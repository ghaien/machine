var vm = new Vue({
    el: '#singleZoneAlarm',
    data: {
        sendData: {
            userName: 'ghaien',
            devId: sessionStorage.getItem("devId"),
            operateType: '10',
            zoneNum: '1',
            secondNum: 1
        },
        sendResult: ''
    },
    methods: {
        comeBack: function () {
            location.href = '/moreOperate'
        },
        // 时间只能设定在1-999之间，并且不能为小数
        changeSecondNum: function () {
            if (!isNaN(vm.sendData.secondNum)) {
                vm.sendData.secondNum = parseInt(vm.sendData.secondNum);
            }
            if (vm.sendData.secondNum > 999) {
                vm.sendData.secondNum = 999;
            } else if (vm.sendData.secondNum < 1) {
                vm.sendData.secondNum = 1;
            }
        },
        sendCommand: function () {
            $.get('/operate', vm.sendData, function (rs) {
                if (rs.code == 0) {
                    vm.sendResult = '设置成功✓';
                } else {
                    vm.sendResult = rs.message;
                }
            });
        }
    }
});