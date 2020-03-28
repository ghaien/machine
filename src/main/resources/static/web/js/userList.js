var userListVm = new Vue({
    el: '#userList',
    data: {
        userList: [
        ],
        userInfo: {
            account: '',
            userName: '',
            password: ''
        }
    },
    methods: {
        showUserList: function () {
            $.get('/userInfo/list', {},
                function (rs) {
                    if (rs.code == 0) {
                        userListVm.userList = rs.data;
                    } else {
                        alert(userListVm.message);
                    }
                }
            );
        },
        showAdd: function () {
            userListVm.userInfo.account = '';
            userListVm.userInfo.userName = '';
            userListVm.userInfo.password = '';
            userListVm.showDetail();
        },
        showUpdate: function (userId) {
            $.get('/userInfo/' + userId,
                function (rs) {
                    if (rs.code == 0) {
                        userListVm.userInfo = rs.data;
                        userListVm.showDetail();
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
            userListVm.showUserList();
            $('#detail').hide();
            $('#list').show();
        },
        addUser: function () {
            $.ajax({
                type: 'POST',
                url: '/userInfo/add',
                data: userListVm.userInfo,
                dataType: 'json',
                success: function (rs) {
                    if (rs.code == 0) {
                        alert("保存成功");
                        userListVm.showList();
                    } else {
                        alert(rs.message);
                    }
                }
            })
        },
        deleteUser: function (id) {
            if (confirm("确定要删除吗？")) {
                $.ajax({
                    type: 'DELETE',
                    url: '/userInfo/' + id,
                    data: {},
                    dataType: 'json',
                    success: function (rs) {
                        if (rs.code == 0) {
                            alert("删除成功");
                            userListVm.showUserList();
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
   userListVm.showUserList();
});