function getUrlParam(name) {
    //构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //匹配目标参数
    var r = window.location.search.substr(1).match(reg);
    //返回参数值
    if (r != null) {
        return decodeURI(r[2]);
    }
    return null;
}

var chinese = {
    devId: '设 备 ',
    nowArm: '当前告警',
    allZoneOperate: '所有防区快速布防设置',
    operate: '操作控制',
    newArm: '最近5次告警记录'
};

var english = {
    devId: 'Device',
    nowArm: 'Current Alarm',
    operate: 'Operation control'
};

var loginTime = '';
$(function () {
    loginTime = sessionStorage.getItem("loginTime");
    if (!loginTime) {
        $.get('/armInfo/nowTime', function (rs) {
            if (rs.code == 0) {
                loginTime = rs.data;
                sessionStorage.setItem("loginTime", loginTime);
            } else {
                alert(rs.message);
            }
        });
    } else {
        $.get('/armInfo/list',
            {
                createTime: loginTime,
                page: 0,
                pageSize: 2
            },
            function (rs) {
                if (rs.code == 0) {
                    for (var i = 0; i < rs.data.length; i++) {
                        var armInfo = rs.data[i];
                        rs.data[i].zoneNo = armInfo.zoneNo < 10 ? '0' + armInfo.zoneNo : armInfo.zoneNo;
                        rs.data[i].armType = armTypeInfo[armInfo.armType];
                    }
                    vm.nowArm = rs.data;
                } else {
                    alert(rs.message);
                }
            }
        )
    }

    $.get('/machineInfo/list', function (rs) {
        if (rs.code == 0) {
            var html = '<option value="">请选择</option>';
            var machines = rs.data.data;
            for (var i = 0; i < machines.length; i++) {
                html += '<option value="' + machines[i].machineNo + '">设备' + (i + 1) + '</option>';
            }
            $('#machineList').html(html);
        } else {
            alert(rs.message);
        }
    })
});

var vm = new Vue({
    el: '#index',
    data: {
        font: chinese,
        sendData: {
            userName: 'ghaien',
            devId: getUrlParam('devId'),
            zoneNum: '255',
            operateType: '5'
        },
        nowArm: [],
        sendResult: '',
        newArm: [
            {
                zoneNo: '01',
                armType: '短路',
                createTime: '2018年06月29日 22:07:30'
            },
            {
                zoneNo: '02',
                armType: '短路',
                createTime: '2018年06月29日 22:07:30'
            },
            {
                zoneNo: '03',
                armType: '短路',
                createTime: '2018年06月29日 22:07:30'
            },
            {
                zoneNo: '04',
                armType: '短路',
                createTime: '2018年06月29日 22:07:30'
            },
            {
                zoneNo: '05',
                armType: '短路',
                createTime: '2018年06月29日 22:07:30'
            }
        ]
    },
    methods: {
        showEnglish: function () {
            vm.font = english;
        },
        showChinese: function () {
            vm.font = chinese;
        },
        subscribe: function () {
            if (!vm.sendData.devId) {
                alert("未选择设备");
                return;
            }
            $.get('/subscribe/' + vm.sendData.userName + "/" + vm.sendData.devId,
                function (rs) {
                    if (rs.code != 0) {
                        alert(rs.message);
                    }
                }
            );
        },
        disSubscribe: function () {
            if (!vm.sendData.devId) {
                alert("未选择设备");
                return;
            }
            $.get('/subscribe/cancel/' + vm.sendData.userName + "/" + vm.sendData.devId,
                function (rs) {
                    if (rs.code != 0) {
                        alert(rs.message);
                    }
                }
            );
        },
        sendCommand: function () {
            vm.sendResult = '';
            if (!vm.sendData.devId) {
                alert('未选择设备');
                return;
            }
            $.get('/operate', vm.sendData, function (rs) {
                if (rs.code == 0) {
                    vm.sendResult = '设置成功✓';
                } else {
                    vm.sendResult = rs.message;
                }
            })
        },
        showNewArm: function () {
            $('#showNewArm').show();
            $('#hideNewArm').hide();
            $('#newArm').show();
        },
        moreOperate: function () {
            if (vm.sendData.devId) {
                sessionStorage.setItem("devId", vm.sendData.devId);
                location.href = '/moreOperate'
            } else {
                alert("未选择设备");
            }
        },
        hideNewArm: function () {
            $('#showNewArm').hide();
            $('#hideNewArm').show();
            $('#newArm').hide();
        }
    }
});

var armTypeInfo = [];
armTypeInfo[0] = '正常';
armTypeInfo[1] = '断路';
armTypeInfo[2] = '短路';
armTypeInfo[3] = '防剪';
armTypeInfo[4] = '触网';
armTypeInfo[5] = '防拆';
armTypeInfo[6] = '电池失效';
armTypeInfo[7] = '泄露';
armTypeInfo[8] = '振动';
armTypeInfo[9] = '张力';
armTypeInfo[80] = '通信断线告警';

$(function () {
    $.get('/armInfo/list',
        {
            page: 0,
            pageSize: 5
        },
        function (rs) {
            if (rs.code == 0) {
                for (var i = 0; i < rs.data.length; i++) {
                    var armInfo = rs.data[i];
                    rs.data[i].zoneNo = armInfo.zoneNo < 10 ? '0' + armInfo.zoneNo : armInfo.zoneNo;
                    rs.data[i].armType = armTypeInfo[armInfo.armType];
                }
                vm.newArm = rs.data;
            } else {
                alert(rs.message);
            }
        }
    )
});