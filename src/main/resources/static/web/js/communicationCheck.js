var vm = new Vue({
    el: '#communicationCheck',
    data: {
        sendData: {
            userName: 'ghaien',
            devId: sessionStorage.getItem("devId"),
            operateType: '',
            devStatus: '0',
            zoneNum: '255'
        },
        checkDisconnectStatus: '0',
        boardEnableStatus: '0',
        setCheckDisconnectMsg: '',
        setBoardEnableMsg: ''
    },
    methods: {
        comeBack: function () {
            location.href = '/moreOperate'
        },
        setCheckDisconnect: function () {
            vm.sendData.operateType = '28';
            vm.sendData.devStatus = vm.checkDisconnectStatus;
            $.get('/operate', vm.sendData, function (rs) {
                if (rs.code == 0) {
                    vm.setCheckDisconnectMsg = '设置成功✓';
                } else {
                    vm.setCheckDisconnectMsg = rs.message;
                }
            });
        },
        setBoardEnable: function () {
            vm.sendData.operateType = '29';
            vm.sendData.devStatus = vm.boardEnableStatus;
            $.get('/operate', vm.sendData, function (rs) {
                if (rs.code == 0) {
                    vm.setBoardEnableMsg = '设置成功✓';
                } else {
                    vm.setBoardEnableMsg = rs.message;
                }
            });
        }
    }
});