;
(function() {
    var vendorPath = '../console/vendor';
    var common = '../console/scripts';
    require.config({
        baseUrl: 'http://localhost:3000/scripts/',
        skipDataMain: true,
        paths: {
            'angular': vendorPath + '/angular/angular',
            'angular-route': vendorPath + '/angular-route/angular-route',
            'angular-resource': vendorPath + '/angular-resource/angular-resource',
            'angular-animate': vendorPath + '/angular-animate/angular-animate',
            'angular-growl': vendorPath + '/angular-growl/build/angular-growl',
            'angular-cookies': vendorPath + '/angular-cookies/angular-cookies',
            'angular-sanitize': vendorPath + '/angular-sanitize/angular-sanitize.min',
            'ui.router': vendorPath + '/angular-ui-router/release/angular-ui-router.min',
            'angular-ui-validate': vendorPath + '/angular-ui-utils/validate.min',
            'ui.bootstrap': vendorPath + '/angular-bootstrap/ui-bootstrap',
            'bindonce': vendorPath + '/angular-bindonce/bindonce',
            'jQuery': vendorPath + '/jquery/jquery.min',
            'highcharts': vendorPath + '/highcharts/highcharts',
            'ng-clip': vendorPath + '/ng-clip/ng-clip',
            'app': 'home/app',
            'bootstrap': 'home/bootstrap',
            'spinjs': vendorPath + '/spinjs/spin',
            'common': common,
            'common-tpl': common + '/aliyun-console-tpl',
            'aliyun-console-bootstrap-tpl': common + '/aliyun-console-bootstrap-tpl',
            'aliyun-home-tpl': 'home/aliyun-home-tpl',
            'angular-locale-zh-cn': vendorPath + '/ng-locale/angular-locale_zh-cn',
            'angular-translate': vendorPath + '/angular-translate/angular-translate'
        },
        shim: {
            'jQuery': { exports: 'jQuery'},
            'angular': { exports: 'angular', deps: ['jQuery']},
            'ui.router': { deps: ['angular']},
            'angular-animate': {deps: ['angular']},
            'angular-growl': { deps: ['angular-animate']},
            'angular-resource': { deps: ['angular']},
            'angular-cookies': { deps: ['angular']},
            'angular-sanitize': { deps: ['angular']},
            'angular-ui-validate': { deps: ['angular']},
            'bindonce': { deps: ['angular']},
            'aliyun-console-bootstrap-tpl': { deps: ['angular']},
            'ui.bootstrap': { deps: ['aliyun-console-bootstrap-tpl']},
            'ng-clip': {deps: ['angular']},
            'highcharts': {deps: ['jQuery']},
            'common-tpl': { deps: ['angular']},
            'spinjs': {},
            'angular-locale-zh-cn': { deps: ['angular']},
            'aliyun-home-tpl': { deps: ['angular']},
            'angular-translate': { deps: ['angular']}
        }
    });
}());
