(function(jsGrid, $, undefined) {

    var Field = jsGrid.Field;
    var isEdited = false;

    function ImageField(config) {
        Field.call(this, config);
    }

    ImageField.prototype = new Field({
        

        autosearch: true,
        readOnly: false,

        filterTemplate: function() {
            return ""; // Không cần filter cho trường ảnh
        },

        insertTemplate: function() {
            if (!this.inserting)
                return "";

            var $result = this.insertControl = this._createFileInput();
            return $result;
        },

        editTemplate: function(value) {
            if (!this.editing)
                return this.itemTemplate.apply(this, arguments);

            var $result = this.editControl = this._createFileInput();

            // Nếu trường đã được sửa đổi, không cho phép chỉnh sửa nữa
            // if (isEdited) {
            //     $result.prop("disabled", true);
            // } else {
            //     $result.val(value);
            // }

            return $result;
        },

        filterValue: function() {
            return ""; // Không cần filter cho trường ảnh
        },

        insertValue: function() {
            return this.insertControl.val();
        },

        editValue: function() {
            return this.editControl.val();
        },

        _createFileInput: function() {
            var self = this;
            var $input = $("<input>").attr("type", "file");

            // Tạo một thẻ <img> để hiển thị hình ảnh
            var $imgPreview = $("<img>").attr("src", "").css("max-width", "100px").css("max-height", "100px").hide();
            var $container = $("<div>").append($imgPreview, $input);

            $input.on("change", function() {
                var file = this.files[0];
                var formData = new FormData();
                formData.append("file", file);

                var xhr = new XMLHttpRequest();
                xhr.open("POST", "./images/upload", true);

                xhr.onload = function() {
                    if (xhr.status === 200) {
                        console.log("Tập tin ảnh đã được tải lên thành công.");
                        var imageUrl = xhr.responseText;
                        self.insertControl.val(imageUrl);
                        // Hiển thị hình ảnh khi đã tải lên thành công
                        $imgPreview.attr("src", imageUrl).show();
                    } else {
                        console.error("Đã xảy ra lỗi khi tải lên tập tin ảnh.");
                    }
                };

                xhr.onerror = function() {
                    console.error("Đã xảy ra lỗi kết nối khi tải lên tập tin ảnh.");
                };

                xhr.send(formData);
            });

            return $container;
        }
    });

    jsGrid.fields.image = jsGrid.ImageField = ImageField;

}(jsGrid, jQuery));
