var loginVm = new Vue({
    el: '#login',
    data: {
        userInfo: {
            account: '',
            password: ''
        },
        loginInfo: ''
    },
    methods: {
        login: function () {
            loginVm.loginInfo = "";
            if (!loginVm.userInfo.account) {
                loginVm.loginInfo = "未输入账号";
                return;
            }
            if (!loginVm.userInfo.password) {
                loginVm.loginInfo = "未输入密码";
                return;
            }
            $.get('/login/' + loginVm.userInfo.account + '/' + loginVm.userInfo.password,
                function (rs) {
                    if (rs.code == 0) {
                        sessionStorage.setItem("account", loginVm.userInfo.account);
                        location.href = "/index"
                    } else {
                        loginVm.loginInfo = rs.message;
                    }
                });
        }
    }
});