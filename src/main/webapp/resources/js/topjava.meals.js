var mealAjaxUrl = "ajax/profile/meals/";
$.datetimepicker.setLocale('ru');

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: "ajax/profile/meals/filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get("ajax/profile/meals/", updateTableByData);
}

$(function () {
    $("#startDate").datetimepicker({
        format: 'Y-m-d',
        timepicker: false
    });
    $("#endDate").datetimepicker({
        format: 'Y-m-d',
        timepicker: false
    });
    $("#startTime").datetimepicker({
        format: 'H:i',
        datepicker: false
    });
    $("#endTime").datetimepicker({
        format: 'H:i',
        datepicker: false
    });
    $("#dateTime").datetimepicker({
        format: 'Y-m-d H:i'
    });
});

$(function () {
    makeEditable({
            ajaxUrl: mealAjaxUrl,
            datatableApi: $("#datatable").DataTable({
                "ajax": {
                    "url": mealAjaxUrl,
                    "dataSrc": ""
                },
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime",
                        "render": function (date, type, row) {
                            if (type === "display") {
                                return date.replace('T', ' ');
                            }
                            return date;
                        }
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    {
                        "orderable": false,
                        "defaultContent": "",
                        "render": renderEditBtn
                    },
                    {
                        "orderable": false,
                        "defaultContent": "",
                        "render": renderDeleteBtn
                    }
                ],
                "order": [
                    [
                        0,
                        "desc"
                    ]
                ],
                "createdRow": function (row, data, dataIndex) {
                    data.excess ? $(row).attr("data-mealExcess", true) : $(row).attr("data-mealExcess", false);
                }
            }),
            updateTable: function () {
                $.get(mealAjaxUrl, updateTableByData);
            }
        }
    );
});