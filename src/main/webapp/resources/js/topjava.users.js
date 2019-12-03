// $(document).ready(function () {
$(function () {
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
        successNoty("Change enabled for user with id " + id);
    });
}