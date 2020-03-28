var vm = new Vue({
    el: '#moreOperate',
    data: {
        sendData: {
            userName: 'ghaien',
            devId: sessionStorage.getItem("devId"),
            operateType: '11',
            zoneNum: '255'
        }
    },
    methods: {
        comeBack: function () {
            location.href = '/web/html/index.html?devId=' + vm.sendData.devId;
        },
        singZoneArming: function () {
            location.href = '/singleZoneArming';
        },
        singleZoneAlarm: function () {
            location.href = '/singleZoneAlarm';
        },
        allZoneAlarm: function () {
            location.href = '/allZoneAlarm';
        },
        allZoneDisAlarm: function () {
            $.get('/operate', vm.sendData, function (rs) {
                if (rs.code == 0) {
                    alert("所有防区报警复位清除成功");
                } else {
                    alert(rs.message);
                }
            });
        },
        singleZoneShear: function () {
            location.href = '/singleZoneShear';
        },
        allZoneShear: function () {
            location.href = '/allZoneShear';
        },
        singleZoneTouch: function () {
            location.href = '/singleZoneTouch';
        },
        allZoneTouch: function () {
            location.href = '/allZoneTouch';
        },
        singleZoneHigh: function () {
            location.href = '/singleZoneHigh';
        },
        allZoneHigh: function () {
            location.href = '/allZoneHigh';
        },
        singleZoneLow: function () {
            location.href = '/singleZoneLow';
        },
        allZoneLow: function () {
            location.href = '/allZoneLow';
        },
        systemTime: function () {
            location.href = '/systemTime';
        },
        timingGroup: function () {
            location.href = '/timingGroup';
        },
        communicationCheck: function () {
            location.href = '/communicationCheck';
        },
        armHistory: function () {
            location.href = '/armHistory';
        }
    }
});