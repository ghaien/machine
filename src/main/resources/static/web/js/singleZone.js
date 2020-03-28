$(function () {
    $.get('/connect/num', {},
        function (rs) {
            if (rs.code == 0) {
                vm.randomNum = rs.data;
            } else {
                alert(rs.message);
            }
        }
    );
});

var vm = new Vue({
    el: '#singleZone',
    data: {
        user: {
            userName: sessionStorage.getItem("account")
        },
        connectStatus: ['连接成功', '连接断开', '与服务器完成连接', '与服务器连接断开'],
        connectInfo: '',
        devId: '',
        subscribeInfo: '',
        operate: {
            zoneNum: 1,
            operateType: 0
        },
        operateInfo: ''
    },
    methods: {
        connect: function () {
            $.get('/connect/' + vm.user.userName, {},
                function (rs) {
                    if (rs.code == 0) {
                        vm.connectInfo = '连接成功';
                    } else {
                        vm.connectInfo = rs.message;
                    }
                }
            );
        },
        disconnect: function () {
            $.get('/connect/disconnect/' + vm.user.userName, {},
                function (rs) {
                    if (rs.code == 0) {
                        vm.connectInfo = '断开连接';
                    } else {
                        vm.connectInfo = rs.message;
                    }
                }
            );
        },
        subscribe: function () {
            if (vm.devId == '') {
                alert('请选择设备');
                return;
            }
            $.get('/subscribe/' + vm.user.userName + "/" + vm.devId, {},
                function (rs) {
                    if (rs.code == 0) {
                        vm.subscribeInfo = '订阅成功';
                    } else {
                        vm.subscribeInfo = rs.message;
                    }
                }
            );
        },
        disSubscribe: function () {
            if (vm.devId == '') {
                alert('请选择设备');
                return;
            }
            $.get('/subscribe/cancel/' + vm.user.userName + "/" + vm.devId, {},
                function (rs) {
                    if (rs.code == 0) {
                        vm.subscribeInfo = '取消订阅';
                    } else {
                        vm.subscribeInfo = rs.message;
                    }
                }
            );
        },
        sendCommand: function () {
            vm.operateInfo = '';
            console.log("operate");
            if (vm.devId == '') {
                alert('请选择设备');
                return;
            }
            $.get('/operate', {
                    userName: vm.user.userName,
                    devId: vm.devId,
                    zoneNum: vm.operate.zoneNum,
                    operateType: vm.operate.operateType
                },
                function (rs) {
                    if (rs.code == 0) {
                        vm.operateInfo = '设置成功✓';
                    } else {
                        vm.operateInfo = rs.message;
                    }
                }
            );
        }
    }
});