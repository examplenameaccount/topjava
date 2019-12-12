var mealAjaxUrl = "ajax/profile/meals/";
$.datetimepicker.setLocale('ru');

$.ajaxSetup({
    converters: {
        "text json": function (result) {
            let newResult = JSON.parse(result);
            if (newResult.dateTime !== undefined) {
                newResult.dateTime = newResult.dateTime.replace("T", " ").substring(0, 16);
            } else if (newResult[0].dateTime !== undefined) {
                newResult.forEach(key => key.dateTime = key.dateTime.replace("T", " ").substring(0, 16));
            }
            return newResult;
        }
    }
});

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
    $("#startDate ,#endDate").datetimepicker({
        format: 'Y-m-d',
        timepicker: false
    });
    $("#startTime, #endTime").datetimepicker({
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
                    $(row).attr("data-mealExcess", data.excess);
                }
            }),
            updateTable: function () {
                $.get(mealAjaxUrl, updateTableByData);
            },
        }
    );
});