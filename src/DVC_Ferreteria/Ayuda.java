/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVC_Ferreteria;

import Vista.dlgAyuda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author User
 */
public class Ayuda implements ActionListener {

    private dlgAyuda dlghelp;

    public Ayuda() {
        this.dlghelp = new dlgAyuda(null, true);

        this.dlghelp.getBtnCaja().addActionListener(this);
        this.dlghelp.getBtnCategoria().addActionListener(this);
        this.dlghelp.getBtnClientes().addActionListener(this);
        this.dlghelp.getBtnEspaniol().addActionListener(this);
        this.dlghelp.getBtnFactura().addActionListener(this);
        this.dlghelp.getBtnFrances().addActionListener(this);
        this.dlghelp.getBtnIngles().addActionListener(this);
        this.dlghelp.getBtnInventario().addActionListener(this);
        this.dlghelp.getBtnJapones().addActionListener(this);
        this.dlghelp.getBtnTrabajadores().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * primero pongo los idiomas de ayuda
         */
        if (e.getSource() == dlghelp.getBtnEspaniol()) { //español 
            dlghelp.getTxtAcercaDe().setText("DVC-Ferretería\n"
                    + "Es un sistema de control de ventas que brinda apoyo al usuario para\n"
                    + "mantener y optimizar la atención de sus clientes. De esta forma, el\n"
                    + "usuario podrá tener el control de la infomación de sus trabajadores,\n"
                    + "clientes, productos y facturas");
            dlghelp.getBtnCaja().setText(" Caja");
            dlghelp.getBtnCategoria().setText(" Categoria");
            dlghelp.getBtnClientes().setText("Clientes");
            dlghelp.getBtnFactura().setText("Factura");
            dlghelp.getBtnInventario().setText("Inventario");
            dlghelp.getBtnTrabajadores().setText("Trabajadores");
            dlghelp.getLblLenguaje().setText("Lenguaje");

            dlghelp.getTxtAyudaCaja().setText("Caja: Su principal funcionamiento es registrar los productos que se desean facturar.\n"
                    + "El programa calcula automaticamente el precio final a pagar tomando en cuenta si el producto tiene descuento o no.\n"
                    + "Se puede añadir un cliente facilmente con solo tocar el botón de añadir cliente, al igual que añadir un producto.\n"
                    + "Se muestra infomación como: el trabajador que está ejecutando la factura, datos del cliente, cantidad de productos seleccionados, entre otros.");
            dlghelp.getTxtAyudaCategoria().setText("Categoria: Permite insertar,buscar, modificar y eliminar cualquier \n"
                    + "categoría que guste, esta misma, ayuda a mantener en orden\n"
                    + "todas las categorías que se encuentran disponibles.");
            dlghelp.getTxtAyudaClientes().setText("Cliente: En esta ventana, se le permite al usuario insertar, \n "
                    + "buscar, modificar y eliminar los datos de los clientes. De\n"
                    + "igual forma controlar los registros que se encuentran y ver todos los clientes disponibles.");
            dlghelp.getTxtAyudaFactura().setText("Facturas: Permite llevar el conteo de facturas realizadas y ver los detalles\n"
                    + " de compra realizada en esa factura.");
            dlghelp.getTxtAyudaInventario().setText("Inventario: En esta opción, se puede \n"
                    + "insertar,buscar, modificar y eliminar los productos deseados.\n"
                    + "Complementariamente, se logra ver todos los productos con sus respectiva información para la comodidad del usuario.");
            dlghelp.getTxtAyudaTrabajadores().setText("Trabajador: En esta ventana, se le permite al usuario\n"
                    + " insertar,buscar, modificar y eliminar los datos de los trabajadores. De\n"
                    + "igual forma controlar los registros que se encuentran y ver todos sus trabajadores.");

        } else if (e.getSource() == dlghelp.getBtnIngles()) { //todo en ingles 

            dlghelp.getTxtAcercaDe().setText("DVC-Hardware store\n"
                    + "It is a sales control system that provides user support for\n"
                    + "maintain and optimize customer service. In this way, the\n"
                    + "user may have control of the information of their workers,\n"
                    + "customers, products and invoices.");
            dlghelp.getBtnCaja().setText(" Box");
            dlghelp.getBtnCategoria().setText("Category");
            dlghelp.getBtnClientes().setText("Customers");
            dlghelp.getBtnFactura().setText("Bill");
            dlghelp.getBtnInventario().setText("Inventory");
            dlghelp.getBtnTrabajadores().setText("workers");
            dlghelp.getLblLenguaje().setText("language");

            dlghelp.getTxtAyudaCaja().setText("Cash: Its main function is to register the products that you want to invoice.\n"
                    + "The program automatically calculates the final price to be paid taking into account whether the product is discounted or not.\n"
                    + "You can easily add a customer just by touching the add customer button, just like adding a product.\n"
                    + "Information is displayed as: the worker who is executing the invoice, customer data, quantity of selected products, among others.");
            dlghelp.getTxtAyudaCategoria().setText("Category: Allows you to insert, search, modify \n "
                    + "and delete any category you like, it helps to keep in order\n"
                    + "all categories that are available.");
            dlghelp.getTxtAyudaClientes().setText("Customer: In this window, the user is allowed to insert, search,\n"
                    + " modify and delete customer data. Of\n"
                    + "Likewise, control the records that are found and see all available clients.");
            dlghelp.getTxtAyudaFactura().setText("Invoices: Allows you to keep count of invoices made\n"
                    + " and see the details of the purchase made on that invoice.");
            dlghelp.getTxtAyudaInventario().setText("Inventory: In this option,\n "
                    + "you can insert, search, modify and delete the desired products.\n"
                    + "Complementary, it is possible to see all the products with their respective information for the comfort of the user.");
            dlghelp.getTxtAyudaTrabajadores().setText("Worker: In this window, the user is allowed to \n "
                    + " insert, search, modify and delete the data of the workers. Of\n"
                    + "Likewise, control the records that are found and see all your workers.");

        } else if (e.getSource() == dlghelp.getBtnFrances()) { //todo en frances
            dlghelp.getTxtAcercaDe().setText("Matériel DVC\n"
                    + "Il s'agit d'un système de contrôle des ventes qui fournit une assistance aux utilisateurs pour\n"
                    + "maintenir et optimiser le service client. De cette façon, le\n"
                    + "l'utilisateur peut avoir le contrôle des informations de ses travailleurs,\n"
                    + "clients, produits et factures.");
            dlghelp.getLblLenguaje().setText("la langue");
            dlghelp.getBtnCaja().setText("Boîte");
            dlghelp.getBtnCategoria().setText("Catégorie");
            dlghelp.getBtnClientes().setText("les Clients");
            dlghelp.getBtnFactura().setText("Facture");
            dlghelp.getBtnInventario().setText("Inventaire");
            dlghelp.getBtnTrabajadores().setText("les Travailleurs");

            dlghelp.getTxtAyudaCaja().setText("Argent comptant: Sa fonction principale est d'enregistrer les produits que vous souhaitez facturer.\n"
                    + "Le programme calcule automatiquement le prix final à payer en tenant compte de la remise ou non du produit.\n"
                    + "Vous pouvez facilement ajouter un client en appuyant simplement sur le bouton Ajouter un client, tout comme l'ajout d'un produit.\n"
                    + "Les informations sont affichées comme: le travailleur qui exécute la facture, les données client, la quantité de produits sélectionnés, entre autres.");
            dlghelp.getTxtAyudaCategoria().setText("Catégorie: Vous permet d'insérer, de rechercher, de modifier \n"
                    + " et de supprimer n'importe quelle catégorie que vous aimez, il aide à garder en ordre\n"
                    + "toutes les catégories disponibles.");
            dlghelp.getTxtAyudaClientes().setText("Client: Dans cette fenêtre, l'utilisateur est autorisé à insérer,\n"
                    + " rechercher, modifier et supprimer des données client. À partir de\n"
                    + "De même, contrôlez les enregistrements trouvés et voyez tous les clients disponibles.");
            dlghelp.getTxtAyudaFactura().setText("Factures: vous permet de compter les factures effectuées et de voir les détails \n"
                    + "de l'achat effectué sur cette facture.");
            dlghelp.getTxtAyudaInventario().setText("Inventaire: Dans cette option, vous pouvez insérer,\n"
                    + " rechercher, modifier et supprimer les produits souhaités.\n"
                    + "En complément, il est possible de voir tous les produits avec leurs informations respectives pour le confort de l'utilisateur.");
            dlghelp.getTxtAyudaTrabajadores().setText("Travailleur: Dans cette fenêtre,\n"
                    + " l'utilisateur est autorisé à insérer, rechercher, modifier et supprimer les données des travailleurs. À partir de\n"
                    + "De même, contrôlez les enregistrements trouvés et voyez tous vos employés.");

        } else if (e.getSource() == dlghelp.getBtnJapones()) {//toda la ayuda en japones 

            dlghelp.getTxtAcercaDe().setText("DVCハードウェア\n"
                    + "それはのためのユーザーサポートを提供する販売管理システムです\n"
                    + "カスタマーサービスの維持と最適化。このように、\n"
                    + "ユーザーは自分の労働者の情報を制御でき、\n"
                    + "顧客、製品、および請求書");
            dlghelp.getLblLenguaje().setText("言語");
            dlghelp.getBtnCaja().setText("ボックス");
            dlghelp.getBtnCategoria().setText("カテゴリー");
            dlghelp.getBtnClientes().setText("お客様");
            dlghelp.getBtnFactura().setText("手形");
            dlghelp.getBtnInventario().setText("在庫");
            dlghelp.getBtnTrabajadores().setText("労働者");

            dlghelp.getTxtAyudaCaja().setText("現金：その主な機能は、請求する製品を登録することです。\n"
                    + "プログラムは、製品が割引かどうかを考慮して、支払われる最終価格を自動的に計算します。\n"
                    + "商品を追加するのと同じように、顧客の追加ボタンをタッチするだけで簡単に顧客を追加できます。\n"
                    + "情報は、請求書を実行している作業者、顧客データ、選択した製品の数量などが表示されます。");
            dlghelp.getTxtAyudaCategoria().setText("カテゴリ：好きなカテゴリを挿入、検索、変更、削除できます。\n"
                    + "利用可能なすべてのカテゴリ");
            dlghelp.getTxtAyudaClientes().setText("顧客：このウィンドウでは、ユーザーは顧客データを挿入、検索、変更、および削除できます。から\n"
                    + "同様に、見つかったレコードを制御し、使用可能なすべてのクライアントを表示します");
            dlghelp.getTxtAyudaFactura().setText("請求書：作成された請求書の数を記録し、その請求書で行われた購入の詳細を確認できます");
            dlghelp.getTxtAyudaInventario().setText("在庫：このオプションでは、目的の製品を挿入、検索、変更、削除できます。\n"
                    + "補足として、ユーザーの快適さのために、すべての製品をそれぞれの情報とともに見ることができます。");
            dlghelp.getTxtAyudaTrabajadores().setText("ワーカー：このウィンドウでは、ユーザーはワーカーのデータを挿入、検索、変更、および削除できます。から\n"
                    + "同様に、見つかったレコードを制御して、すべてのワーカーを表示します。");

        } else if (e.getSource() == dlghelp.getBtnCaja()) {
            this.dlghelp.getPanInformacion().setSelectedIndex(1);

        } else if (e.getSource()==dlghelp.getBtnTrabajadores()) {
            this.dlghelp.getPanInformacion().setSelectedIndex(2);

        }else if (e.getSource()==dlghelp.getBtnClientes()) {
            this.dlghelp.getPanInformacion().setSelectedIndex(3);

        }else if (e.getSource()==dlghelp.getBtnInventario()) {
            this.dlghelp.getPanInformacion().setSelectedIndex(4);

        }else if (e.getSource()==dlghelp.getBtnCategoria()) {
            this.dlghelp.getPanInformacion().setSelectedIndex(5);

        }else if (e.getSource()==dlghelp.getBtnFactura()) {
            this.dlghelp.getPanInformacion().setSelectedIndex(6);

        }

    }

    /**
     * metodo para llamar para inicar la vista
     */
    public void ventana() {
        this.dlghelp.setLocationRelativeTo(null);
        dlghelp.getPanInformacion().setSelectedIndex(0);
        dlghelp.getTxtAcercaDe().setText("DVC-Ferretería\n"
                + "Es un sistema de control de ventas que brinda apoyo al usuario para\n"
                + "mantener y optimizar la atención de sus clientes. De esta forma, el\n"
                + "usuario podrá tener el control de la infomación de sus trabajadores,\n"
                + "clientes, productos y facturas");
        dlghelp.getBtnCaja().setText(" Caja");
        dlghelp.getBtnCategoria().setText(" Categoria");
        dlghelp.getBtnClientes().setText("Clientes");
        dlghelp.getBtnFactura().setText("Factura");
        dlghelp.getBtnInventario().setText("Inventario");
        dlghelp.getBtnTrabajadores().setText("Trabajadores");
        dlghelp.getLblLenguaje().setText("Lenguaje");

        dlghelp.getTxtAyudaCaja().setText("Caja: Su principal funcionamiento es registrar los productos que se desean facturar.\n"
                + "El programa calcula automaticamente el precio final a pagar tomando en cuenta si el producto tiene descuento o no.\n"
                + "Se puede añadir un cliente facilmente con solo tocar el botón de añadir cliente, al igual que añadir un producto.\n"
                + "Se muestra infomación como: el trabajador que está ejecutando la factura, datos del cliente, cantidad de productos seleccionados, entre otros.");
        dlghelp.getTxtAyudaCategoria().setText("Categoria: Permite insertar,buscar, modificar y eliminar cualquier \n"
                + "categoría que guste, esta misma, ayuda a mantener en orden\n"
                + "todas las categorías que se encuentran disponibles.");
        dlghelp.getTxtAyudaClientes().setText("Cliente: En esta ventana, se le permite al usuario insertar, \n "
                + "buscar, modificar y eliminar los datos de los clientes. De\n"
                + "igual forma controlar los registros que se encuentran y ver todos los clientes disponibles.");
        dlghelp.getTxtAyudaFactura().setText("Facturas: Permite llevar el conteo de facturas realizadas y ver los detalles\n"
                + " de compra realizada en esa factura.");
        dlghelp.getTxtAyudaInventario().setText("Inventario: En esta opción, se puede \n"
                + "insertar,buscar, modificar y eliminar los productos deseados.\n"
                + "Complementariamente, se logra ver todos los productos con sus respectiva información para la comodidad del usuario.");
        dlghelp.getTxtAyudaTrabajadores().setText("Trabajador: En esta ventana, se le permite al usuario\n"
                + " insertar,buscar, modificar y eliminar los datos de los trabajadores. De\n"
                + "igual forma controlar los registros que se encuentran y ver todos sus trabajadores.");
        dlghelp.setVisible(true);
    }

}
