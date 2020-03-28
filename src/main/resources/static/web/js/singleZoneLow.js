var vm = new Vue({
    el: '#singleZoneLow',
    data: {
        sendData: {
            userName: 'ghaien',
            devId: sessionStorage.getItem("devId"),
            operateType: '20',
            zoneNum: '1',
            pressureValue: 0.8
        },
        sendResult: ''
    },
    methods: {
        comeBack: function () {
            location.href = '/moreOperate'
        },
        // 低压值设置范围为0.8-2KV，保留一位小数
        changePressureValue: function () {
            if (!isNaN(vm.sendData.pressureValue)) {
                vm.sendData.pressureValue = parseFloat(vm.sendData.pressureValue) == parseInt(vm.sendData.pressureValue) ?
                    parseFloat(vm.sendData.pressureValue) : parseFloat(vm.sendData.pressureValue).toFixed(1);
            }
            if (vm.sendData.pressureValue > 2) {
                vm.sendData.pressureValue = 2;
            } else if (vm.sendData.pressureValue < 0.8) {
                vm.sendData.pressureValue = 0.8;
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