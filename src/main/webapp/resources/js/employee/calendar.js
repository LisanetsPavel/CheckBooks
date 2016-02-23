/**
 * Created by pc8 on 06.08.15.
 */


$(document).ready(function() {


    var $date_field = $('input[name="date"]');
    $date_field.minical({
        date_format: function (date) {
            return [date.getDate(), date.getMonth() + 1, date.getFullYear()].join("-");
        }
    });
    var $date_field = $('input[name="dateStart"]');
    $date_field.minical({
        date_format: function (date) {
            return [date.getDate(), date.getMonth() + 1, date.getFullYear()].join("-");
        }
    });

    var $date_field = $('input[name="dateEnd"]');
    $date_field.minical({
        date_format: function (date) {
            return [date.getDate(), date.getMonth() + 1, date.getFullYear()].join("-");
        }
    });

});