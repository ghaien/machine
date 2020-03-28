var vm = new Vue({
    el: '#systemTime',
    data: {
        sendData: {
            userName: 'ghaien',
            devId: sessionStorage.getItem("devId"),
            operateType: '21',
            operateTime: '',
            zoneNum: '255'
        },
        sendResult: ''
    },
    methods: {
        comeBack: function () {
            location.href = '/moreOperate'
        },
        sendCommand: function () {
            vm.sendData.operateTime = $('#year').val() + "-" + $('#month').val() + "-" + $('#day').val() + " "
                + $('#hour').val() + ":" + $('#minute').val() + ":" + $('#second').val()
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

$(function () {
    var currYear = (new Date()).getFullYear();
    var opt={};
    opt.datetime = {preset : 'datetime'};
    opt.default = {
        theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式
        mode: 'scroller', //日期选择模式
        dateFormat: 'yyyy-mm-dd',
        lang: 'zh',
        showNow: true,
        nowText: "今天",
        startYear: currYear - 10, //开始年份
        endYear: currYear + 10 //结束年份
    };

    var optDateTime = $.extend(opt['datetime'], opt['default']);
    $("#year").mobiscroll(optDateTime).datetime(optDateTime);
    $("#month").mobiscroll(optDateTime).datetime(optDateTime);
    $("#day").mobiscroll(optDateTime).datetime(optDateTime);
    $("#hour").mobiscroll(optDateTime).datetime(optDateTime);
    $("#minute").mobiscroll(optDateTime).datetime(optDateTime);
    $("#second").mobiscroll(optDateTime).datetime(optDateTime);
});

setInterval(function () {
    var time;
    if ($('#year').val().length > 4) {
        time = $('#year').val();
    } else if ($('#month').val().length > 2) {
        time = $('#month').val();
    } else if ($('#day').val().length > 2) {
        time = $('#day').val();
    } else if ($('#hour').val().length > 2) {
        time = $('#hour').val();
    } else if ($('#minute').val().length > 2) {
        time = $('#minute').val();
    } else if ($('#second').val().length > 2) {
        time = $('#second').val();
    }
    if (time) {
        $('#year').val(time.substring(0, 4));
        $('#month').val(time.substring(5, 7));
        $('#day').val(time.substring(8, 10));
        $('#hour').val(time.substring(11, 13));
        $('#minute').val(time.substring(14, 16));
        $('#second').val(time.substring(17, 19));
    }
}, 100);

$(function () {
    $.get('/armInfo/nowTime', function (rs) {
        if (rs.code == 0) {
            vm.operateTime = rs.data;
            $('#year').val(rs.data.substring(0, 4));
            $('#month').val(rs.data.substring(5, 7));
            $('#day').val(rs.data.substring(8, 10));
            $('#hour').val(rs.data.substring(11, 13));
            $('#minute').val(rs.data.substring(14, 16));
            $('#second').val(rs.data.substring(17, 19));
        } else {
            alert(rs.message);
        }
    });
});