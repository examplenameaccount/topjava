// $(document).ready(function () {
$(function () {
    let id;
    $('body').on('change', ':checked', function () {
        id = $(this).closest("tr").attr("id");
        changeEnabled(id, true);
    }).on('change', 'input:checkbox:not(:checked)', function () {
        id = $(this).closest("tr").attr("id");
        changeEnabled(id, false);
    });
    makeEditable({
            ajaxUrl: "ajax/admin/users/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "name"
                    },
                    {
                        "data": "email"
                    },
                    {
                        "data": "roles"
                    },
                    {
                        "data": "enabled"
                    },
                    {
                        "data": "registered"
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
                        "asc"
                    ],
                    [
                        4,
                        "asc"
                    ]
                ]
            })
        }
    );
});

function changeEnabled(id, enabled) {
    $.ajax({
        url: context.ajaxUrl + id + "/?" + $.param({enabled: enabled}),
        type: "PATCH"
    }).done(function () {
        if (enabled) {
            $("tr[id=" + id + "]").removeClass("disabled").addClass("enabled");
        } else {
            $("tr[id=" + id + "]").removeClass("enabled").addClass("disabled")
        }
        successNoty("Change enabled for user with id " + id);
    }).fail(function () {
        failNoty("Failed change enabled for user with id " + id)
    });
}

function saveUser() {
    $.ajax({
        type: "POST",
        url: context.ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
        successNoty("Saved");
    }).fail(function () {
        failNoty("Failed save user with id " + id)
    });
}