var vm = new Vue({
    el: '#timingGroup',
    data: {
        sendData: {
            userName: 'ghaien',
            devId: sessionStorage.getItem("devId"),
            operateType: '',
            armType: '',
            startArm: '',
            endArm: '',
            zoneNum: '255'
        },
        sendResult1: '',
        sendResult2: '',
        sendResult3: '',
        sendResult4: '',
        sendResult5: '',
        sendResult6: '',
        armType1: '4',
        armType2: '4',
        armType3: '4',
        armType4: '4',
        armType5: '4',
        armType6: '4'
    },
    methods: {
        comeBack: function () {
            location.href = '/moreOperate'
        },
        sendCommand: function (num) {
            if (!$('#group' + num).is(':checked')) {
                return;
            }
            var hour1 = $('#hour' + ((num * 2) - 1)).val();
            var hour2 = $('#hour' + (num * 2)).val();
            var minute1 = $('#minute' + ((num * 2)- 1));
            var minute2 = $('#minute' + (num * 2));
            if (hour2 < hour1) {
                alert("撤防时间不能小于布防时间");
                return;
            } else if (hour1 == hour2) {
                if (minute2 < minute1) {
                    alert("撤防时间不能小于布防时间");
                    return;
                }
            }
            vm.sendData.operateType = 21 + num;
            vm.sendData.armType = vm['armType' + num];
            vm.sendData.startArm = $('#hour' + ((num * 2) - 1)).val() + ":" + $('#minute' + ((num * 2) - 1)).val() + ":00";
            vm.sendData.endArm = $('#hour' + (num * 2)).val() + ":" + $('#minute' + (num * 2)).val() + ":00";
            $.get('/operate', vm.sendData, function (rs) {
                if (rs.code == 0) {
                    vm['sendResult' + num] = '设置成功✓';
                } else {
                    vm['sendResult' + num] = rs.message;
                }
            });
        }
    }
});

$(function () {
    var currYear = (new Date()).getFullYear();
    var opt={};
    opt.time = {preset : 'time'};
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

    var optTime = $.extend(opt['time'], opt['default']);
    $("#hour1").mobiscroll(optTime).time(optTime);
    $("#hour2").mobiscroll(optTime).time(optTime);
    $("#hour3").mobiscroll(optTime).time(optTime);
    $("#hour4").mobiscroll(optTime).time(optTime);
    $("#hour5").mobiscroll(optTime).time(optTime);
    $("#hour6").mobiscroll(optTime).time(optTime);
    $("#hour7").mobiscroll(optTime).time(optTime);
    $("#hour8").mobiscroll(optTime).time(optTime);
    $("#hour9").mobiscroll(optTime).time(optTime);
    $("#hour10").mobiscroll(optTime).time(optTime);
    $("#hour11").mobiscroll(optTime).time(optTime);
    $("#hour12").mobiscroll(optTime).time(optTime);
    $("#minute1").mobiscroll(optTime).time(optTime);
    $("#minute2").mobiscroll(optTime).time(optTime);
    $("#minute3").mobiscroll(optTime).time(optTime);
    $("#minute4").mobiscroll(optTime).time(optTime);
    $("#minute5").mobiscroll(optTime).time(optTime);
    $("#minute6").mobiscroll(optTime).time(optTime);
    $("#minute7").mobiscroll(optTime).time(optTime);
    $("#minute8").mobiscroll(optTime).time(optTime);
    $("#minute9").mobiscroll(optTime).time(optTime);
    $("#minute10").mobiscroll(optTime).time(optTime);
    $("#minute11").mobiscroll(optTime).time(optTime);
    $("#minute12").mobiscroll(optTime).time(optTime);
});

setInterval(function () {
    for (var i = 1; i <= 12; i++) {
        var time = '';
        if ($('#hour' + i).val().length > 2) {
            time = $('#hour' + i).val();
        } else if ($('#minute' + i).val().length > 2) {
            time = $('#minute' + i).val();
        }
        if (time) {
            $('#hour' + i).val(time.substring(0, 2));
            $('#minute' + i).val(time.substring(3, 5));
        }
    }
}, 100);