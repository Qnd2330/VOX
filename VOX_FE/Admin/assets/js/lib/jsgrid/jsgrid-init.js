        $(function() {

            $("#jsGrid").jsGrid({
                height: "100%",
                width: "100%",
                filtering: false,
                editing: true,
                inserting: true,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 15,
                pageButtonCount: 5,
                deleteConfirm: "Do you really want to delete the client?",
                controller: db,
                fields: [
                    { name: "ID", type: "text", width: 150 },
                    { name: "Cân nặng", type: "number", width: 100 },
                    { name: "Giá", type: "text", width: 100 },
                    { name: "Loại quần áo", type: "select",width: 150, items: db.clothtype, valueField: "Id", textField: "Name" },
                    { name: "Kiểu giặt", type: "select",width: 150, items: db.washtype, valueField: "Id", textField: "Name" },
                    { name: "Trạng thái", type: "checkbox",width: 70, title: "Đã hoàn thành", sorting: false },
                    { type: "control" }
                ]
            });

        });