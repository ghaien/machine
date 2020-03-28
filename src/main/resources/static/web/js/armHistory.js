var vm = new Vue({
    el: '#armHistory',
    data: {
        armHistory: []
    },
    methods: {
        comeBack: function () {
            location.href = '/moreOperate'
        }
    }
});

var armTypeInfo = [];
armTypeInfo[0] = '正常';
armTypeInfo[1] = '断路';
armTypeInfo[2] = '短路';
armTypeInfo[3] = '防剪';
armTypeInfo[4] = '触网';
armTypeInfo[5] = '防拆';
armTypeInfo[6] = '电池失效';
armTypeInfo[7] = '泄露';
armTypeInfo[8] = '振动';
armTypeInfo[9] = '张力';
armTypeInfo[80] = '通信断线告警';

$(function () {
    $.get('/armInfo/list',
        {
            page: 0,
            pageSize: 500
        },
        function (rs) {
            if (rs.code == 0) {
                for (var i = 0; i < rs.data.length; i++) {
                    var armInfo = rs.data[i];
                    rs.data[i].zoneNo = armInfo.zoneNo < 10 ? '0' + armInfo.zoneNo : armInfo.zoneNo;
                    rs.data[i].armType = armTypeInfo[armInfo.armType];
                }
                vm.armHistory = rs.data;
            } else {
                alert(rs.message);
            }
        }
    )
});

