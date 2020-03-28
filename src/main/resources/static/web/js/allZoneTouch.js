var vm = new Vue({
    el: '#allZoneTouch',
    data: {
        sendData: {
            userName: 'ghaien',
            devId: sessionStorage.getItem("devId"),
            operateType: '14',
            sensitivity: 1,
            zoneNum: '255'
        },
        sendResult: ''
    },
    methods: {
        comeBack: function () {
            location.href = '/moreOperate'
        },
        // 灵敏度设置范围为1-9，不能为小数
        changeSensitivity: function () {
            if (!isNaN(vm.sendData.sensitivity)) {
                vm.sendData.sensitivity = parseInt(vm.sendData.sensitivity);
            }
            if (vm.sendData.sensitivity > 9) {
                vm.sendData.sensitivity = 9;
            } else if (vm.sendData.sensitivity < 1) {
                vm.sendData.sensitivity = 1;
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