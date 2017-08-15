// Setup the filter pm-status
appModule.filter('pmStatus', function () {
    return function (str) {
        //     N = New
        //     I = In Progress
        //     W = Waitlisted Lv1
        //     V = Waitlisted Lv2
        //     C = Completed
        str = str || '' ;
        // if(str){
             if (str.toUpperCase() === 'N') {
                return 'New';
            } else if (str.toUpperCase() === 'I') {
                return 'In Progress';
            } else if (str.toUpperCase() === 'W') {
                return 'Waitlisted Lv1';
            } else if (str.toUpperCase() === 'V') {
                return 'Waitlisted Lv2';
            } else if (str.toUpperCase() === 'C') {
                return 'Completed';
            } else {
                return str;
            }
    };
});

//  filter for show percent-overall
appModule.filter('percentage', function () {
    return function (str) {
        if(str == undefined || str == null || isNaN(parseFloat(str))){
            return '-';
        }
        return  str + '%' ;

    };
});