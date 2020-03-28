var vm = new Vue({
    el: '#singleZoneHigh',
    data: {
        sendData: {
            userName: 'ghaien',
            devId: sessionStorage.getItem("devId"),
            operateType: '19',
            zoneNum: '1',
            pressureValue: 4.5
        },
        sendResult: ''
    },
    methods: {
        comeBack: function () {
            location.href = '/moreOperate'
        },
        // 高压值设置范围为4.5-7KV，保留一位小数
        changePressureValue: function () {
            if (!isNaN(vm.sendData.pressureValue)) {
                vm.sendData.pressureValue = parseFloat(vm.sendData.pressureValue) == parseInt(vm.sendData.pressureValue) ?
                    parseFloat(vm.sendData.pressureValue) : parseFloat(vm.sendData.pressureValue).toFixed(1);
            }
            if (vm.sendData.pressureValue > 7) {
                vm.sendData.pressureValue = 7;
            } else if (vm.sendData.pressureValue < 4.5) {
                vm.sendData.pressureValue = 4.5;
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