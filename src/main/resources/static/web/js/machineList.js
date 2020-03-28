var machineListVm = new Vue({
    el: '#machineList',
    data: {
        machineList: [
        ],
        machineInfo: {
            machineNo: ''
        }
    },
    methods: {
        showMachineList: function () {
            $.get('/machineInfo/list', {},
                function (rs) {
                    if (rs.code == 0) {
                        machineListVm.machineList = rs.data.data;
                    } else {
                        alert(machineListVm.message);
                    }
                }
            );
        },
        showAdd: function () {
            machineListVm.machineInfo.machineNo = '';
            machineListVm.showDetail();
        },
        showUpdate: function (machineId) {
            $.get('/machineInfo/' + machineId,
                function (rs) {
                    if (rs.code == 0) {
                        machineListVm.machineInfo = rs.data;
                        machineListVm.showDetail();
                    } else {
                        alert(rs.message);
                    }
                });
        },
        showDetail: function () {
            $('#list').hide();
            $('#detail').show();
        },
        showList: function () {
            machineListVm.showMachineList();
            $('#detail').hide();
            $('#list').show();
        },
        updateMachine: function () {
            $.ajax({
                type: 'POST',
                url: '/machineInfo/update',
                data: machineListVm.machineInfo,
                dataType: 'json',
                success: function (rs) {
                    if (rs.code == 0) {
                        alert("保存成功");
                        machineListVm.showList();
                    } else {
                        alert(rs.message);
                    }
                }
            })
        },
        deleteMachine: function (id) {
            if (confirm("确定要删除吗？")) {
                $.ajax({
                    type: 'DELETE',
                    url: '/machineInfo/' + id,
                    data: {},
                    dataType: 'json',
                    success: function (rs) {
                        if (rs.code == 0) {
                            machineListVm.showMachineList();
                            alert("删除成功");
                        } else {
                            alert(rs.message);
                        }
                    }
                })
            }
        }
    }
});

$(function () {
   machineListVm.showMachineList();
});