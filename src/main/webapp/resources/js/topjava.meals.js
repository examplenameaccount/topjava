$(function () {
    $(".filter").click(function () {
        filter();
    });
    $(".reset").click(function () {
        filterFormData = undefined;
        updateTable();
    });
    makeEditable({
            ajaxUrl: "ajax/meals/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "desc"
                    ]
                ]
            })
        }
    );
});

function filter() {
    if (filterFormData === undefined) {
        filterFormData = $("#filterForm").serialize();
    }
    $.get({
        url: context.ajaxUrl + "filter",
        type: "GET",
        data: filterFormData
    }).done(function (data) {
        context.datatableApi.clear().rows.add(data).draw();
    });
}

function saveMeal() {
    $.ajax({
        type: "POST",
        url: context.ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        if (filterFormData === undefined) {
            updateTable();
        } else {
            filter();
        }
        successNoty("Saved");
    }).fail(function () {
        failNoty("Failed save meal")
    });
}