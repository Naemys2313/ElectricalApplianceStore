package com.naemys.electricalappliancestore.units;

public class Unit {

    public static final String TABLE_EXTRA = "table";
    public static final String UPDATE_EXTRA = "update";
    public static final String POSITION_EXTRA = "position";
    public static final String DATA_EXTRA = "data";
    public static final String INSERT_ID_EXTRA = "insert_id";

    public static final int ADD_CODE_REQUEST = 1;
    public static final int UPDATE_CODE_REQUEST = 2;
    public static final int DELETE_CODE_REQUEST = 3;


    public static final String _ID = "id";

    public static final String URL_SIGN_UP = "https://elecronicapplicatestore.000webhostapp.com/sign/sign_up.php";
    public static final String URL_SIGN_IN = "https://elecronicapplicatestore.000webhostapp.com/sign/sign_in.php";


    public static class Carts {
        public static final String TABLE_NAME = "carts";

        public static final String _GOODS_ID = "goods_id";
        public static final String _QUANTITY = "quantity";
        public static final String _ORDER_ID = "order_id";

        public static final String URL_GET_ALL =
                "https://elecronicapplicatestore.000webhostapp.com/carts/get_all_carts.php";

        public static final String URL_CREATE =
                "https://elecronicapplicatestore.000webhostapp.com/carts/create_cart.php";

        public static final String URL_UPDATE =
                "https://elecronicapplicatestore.000webhostapp.com/carts/update_cart.php";

        public static final String URL_DELETE =
                "https://elecronicapplicatestore.000webhostapp.com/carts/delete_cart.php";

    }

    public static class Clients {
        public static final String TABLE_NAME = "clients";

        public static final String _FIRST_NAME = "first_name";
        public static final String _LAST_NAME = "last_name";
        public static final String _MIDDLE_NAME = "middle_name";
        public static final String _DISCOUNT = "discount";

        public static final String URL_GET_ALL =
                "https://elecronicapplicatestore.000webhostapp.com/clients/get_all_clients.php";

        public static final String URL_CREATE =
                "https://elecronicapplicatestore.000webhostapp.com/clients/create_clients.php";

        public static final String URL_UPDATE =
                "https://elecronicapplicatestore.000webhostapp.com/clients/update_client.php";

        public static final String URL_DELETE =
                "https://elecronicapplicatestore.000webhostapp.com/clients/delete_client.php";

    }

    public static class Delivery {
        public static final String TABLE_NAME = "delivery";

        public static final String _ADDRESS = "address";
        public static final String _DELIVERED = "delivered";
        public static final String _DATE_TIME = "date_time";
        public static final String _ORDER_ID = "order_id";

        public static final String URL_GET_ALL =
                "https://elecronicapplicatestore.000webhostapp.com/delivery/get_all_delivery.php";

        public static final String URL_CREATE =
                "https://elecronicapplicatestore.000webhostapp.com/delivery/create_delivery.php";

        public static final String URL_UPDATE =
                "https://elecronicapplicatestore.000webhostapp.com/delivery/update_delivery.php";

        public static final String URL_DELETE =
                "https://elecronicapplicatestore.000webhostapp.com/delivery/delete_delivery.php";

    }

    public static class Goods {
        public static final String TABLE_NAME = "goods";

        public static final String _NAME = "name";
        public static final String _TYPE_ID = "type_id";
        public static final String _QUANTITY_IN_STOCK = "quantity_in_stock";
        public static final String _DESCRIPTION = "description";

        public static final String URL_GET_ALL =
                "https://elecronicapplicatestore.000webhostapp.com/goods/get_all_goods.php";

        public static final String URL_CREATE =
                "https://elecronicapplicatestore.000webhostapp.com/goods/create_goods.php";

        public static final String URL_UPDATE =
                "https://elecronicapplicatestore.000webhostapp.com/goods/update_goods.php";

        public static final String URL_DELETE =
                "https://elecronicapplicatestore.000webhostapp.com/goods/delete_goods.php";

    }

    public static class Orders {
        public static final String TABLE_NAME = "orders";

        public static final String _CLIENT_ID = "client_id";
        public static final String _PAYMENT_METHOD_ID = "payment_method_id";
        public static final String _PAID = "paid";
        public static final String _DATE_TIME = "date_time";

        public static final String URL_GET_ALL =
                "https://elecronicapplicatestore.000webhostapp.com/orders/get_all_orders.php";

        public static final String URL_CREATE =
                "https://elecronicapplicatestore.000webhostapp.com/orders/create_order.php";

        public static final String URL_UPDATE =
                "https://elecronicapplicatestore.000webhostapp.com/orders/update_order.php";

        public static final String URL_DELETE =
                "https://elecronicapplicatestore.000webhostapp.com/orders/delete_order.php";

    }

    public static class PaymentMethods {
        public static final String TABLE_NAME = "payment_methods";

        public static final String _NAME = "name";

        public static final String URL_GET_ALL =
                "https://elecronicapplicatestore.000webhostapp.com/payment_methods/get_all_payment_methods.php";

        public static final String URL_CREATE =
                "https://elecronicapplicatestore.000webhostapp.com/payment_methods/create_payment_method.php";

        public static final String URL_UPDATE =
                "https://elecronicapplicatestore.000webhostapp.com/payment_methods/update_payment_method.php";

        public static final String URL_DELETE =
                "https://elecronicapplicatestore.000webhostapp.com/payment_methods/delete_payment_method.php";

    }

    public static class Procurement {
        public static final String TABLE_NAME = "procurement";

        public static final String _GOODS_ID = "goods_id";
        public static final String _SUPPLIER_ID = "supplier_id";
        public static final String _PRICE = "price";

        public static final String URL_GET_ALL =
                "https://elecronicapplicatestore.000webhostapp.com/procurement/get_all_procurements.php";

        public static final String URL_CREATE =
                "https://elecronicapplicatestore.000webhostapp.com/procurement/create_procurement.php";

        public static final String URL_UPDATE =
                "https://elecronicapplicatestore.000webhostapp.com/procurement/update_procurement.php";

        public static final String URL_DELETE =
                "https://elecronicapplicatestore.000webhostapp.com/procurement/delete_procurement.php";

    }

    public static class Reviews {
        public static final String TABLE_NAME = "reviews";

        public static final String _GOODS_ID = "goods_id";
        public static final String _CLIENT_ID = "client_id";
        public static final String _REVIEW_TEXT = "review_text";
        public static final String _RATING = "rating";

        public static final String URL_GET_ALL =
                "https://elecronicapplicatestore.000webhostapp.com/reviews/get_all_reviews.php";

        public static final String URL_CREATE =
                "https://elecronicapplicatestore.000webhostapp.com/reviews/create_review.php";

        public static final String URL_UPDATE =
                "https://elecronicapplicatestore.000webhostapp.com/reviews/update_review.php";

        public static final String URL_DELETE =
                "https://elecronicapplicatestore.000webhostapp.com/reviews/delete_review.php";


    }

    public static class Sale {
        public static final String TABLE_NAME = "sale";

        public static final String _GOODS_ID = "goods_id";
        public static final String _PRICE = "price";
        public static final String _DISCOUNT = "discount";

        public static final String URL_GET_ALL =
                "https://elecronicapplicatestore.000webhostapp.com/sale/get_all_sale.php";

        public static final String URL_CREATE =
                "https://elecronicapplicatestore.000webhostapp.com/sale/create_sale.php";

        public static final String URL_UPDATE =
                "https://elecronicapplicatestore.000webhostapp.com/sale/update_sale.php";

        public static final String URL_DELETE =
                "https://elecronicapplicatestore.000webhostapp.com/sale/delete_sale.php";

    }

    public static class Suppliers {
        public static final String TABLE_NAME = "suppliers";

        public static final String _FIRST_NAME = "first_name";
        public static final String _LAST_NAME = "last_name";
        public static final String _MIDDLE_NAME = "middle_name";
        public static final String _PHONE = "phone";

        public static final String URL_GET_ALL =
                "https://elecronicapplicatestore.000webhostapp.com/suppliers/get_all_suppliers.php";

        public static final String URL_CREATE =
                "https://elecronicapplicatestore.000webhostapp.com/suppliers/create_supplier.php";

        public static final String URL_UPDATE =
                "https://elecronicapplicatestore.000webhostapp.com/suppliers/update_supplier.php";

        public static final String URL_DELETE =
                "https://elecronicapplicatestore.000webhostapp.com/suppliers/delete_supplier.php";

    }

    public static class TypesOfGoods {
        public static final String TABLE_NAME = "types_of_goods";

        public static final String _NAME = "name";

        public static final String URL_GET_ALL =
                "https://elecronicapplicatestore.000webhostapp.com/types_of_goods/get_all_types.php";

        public static final String URL_CREATE =
                "https://elecronicapplicatestore.000webhostapp.com/types_of_goods/create_type.php";

        public static final String URL_UPDATE =
                "https://elecronicapplicatestore.000webhostapp.com/types_of_goods/update_type.php";

        public static final String URL_DELETE =
                "https://elecronicapplicatestore.000webhostapp.com/types_of_goods/delete_type.php";

    }
}
