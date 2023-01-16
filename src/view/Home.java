/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.CategoryDao;
import dao.ExportDao;
import dao.ExportDetailDao;
import dao.ImportDao;
import dao.ImportDetailDao;
import dao.ProductDao;
import dao.StaffDao;
import dao.StatisticalDao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Account;
import model.Category;
import model.Export;
import model.ExportDetail;
import model.Import;
import model.ImportDetail;
import model.Product;
import model.Staff;

/**
 *
 * @author duyanh
 */
public class Home extends javax.swing.JFrame {

    private ArrayList<Product> list;
    DefaultTableModel tableModel;
    DefaultTableModel tableSearchModel;
    DefaultTableModel tablePModel;
    DefaultTableModel tablePIModel;
    DefaultTableModel tablePI2Model;
    DefaultTableModel tableEModel;
    DefaultTableModel tableEDModel;
    DefaultTableModel tableModelCate;
    DefaultTableModel tableModelStaff;
    DefaultTableModel tableModelImport;
    DefaultTableModel tableModelImportDetail;
    DefaultTableModel tableModelStat;
    DefaultTableModel tableModelStatProduct;

    ProductDao productDao = new ProductDao();
    ExportDao exportDao = new ExportDao();
    ImportDao importDao = new ImportDao();
    ImportDetailDao importDtDao = new ImportDetailDao();
    ExportDetailDao exportDtDao = new ExportDetailDao();
    CategoryDao categoryDao = new CategoryDao();
    StaffDao staffDao = new StaffDao();

    /**
     * Creates new form
     */
    public Home() {

//        Login login = new Login();
//        login.setVisible(true);
//        this.dispose();
        initComponents();
        tableModel = (DefaultTableModel) tbProduct.getModel();
        tableSearchModel = (DefaultTableModel) tbSearchProduct.getModel();
        tablePModel = (DefaultTableModel) tbShowP.getModel();
        tableEModel = (DefaultTableModel) tbExport.getModel();
        tableEDModel = (DefaultTableModel) tbExportDetail.getModel();
        tableModelCate = (DefaultTableModel) tbCategory.getModel();
        tableModelStaff = (DefaultTableModel) tbStaff.getModel();
        tablePIModel = (DefaultTableModel) tbSearchProduct2.getModel();
        tablePI2Model = (DefaultTableModel) tbShowP2.getModel();
        tableModelImport = (DefaultTableModel) tbImport.getModel();
        tableModelImportDetail = (DefaultTableModel) tbImportDt.getModel();
        tableModelStat = (DefaultTableModel) tbStat.getModel();
        tableModelStatProduct = (DefaultTableModel) tbStatProduct.getModel();
        Account account = Account.getInstance(0, "", 0);
        if (account.position == 0) {
            tbStaff.setVisible(false);
            JPaneNV.setVisible(false);
            tbStaff.setVisible(false);
            txtQuantityW3.setVisible(false);
            jPanel23.setVisible(false);
            jPanel18.setVisible(false);
            jPanel15.setVisible(false);
            tbSearchProduct2.setVisible(false);
            jPanel26.setVisible(false);
            jPanel5.setVisible(false);
            jPanelCate.setVisible(false);
        }
        lbStaffName.setText(account.staffName);
        try {
            showTbProduct();
            showTbExport();
            showTbCategory();
            showTbStaff();
            showCatalog();
            showTbProductIn();
            showTbImport();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showCatalog() throws SQLException {
        jCatalog.removeAllItems();
        try {
            List<Object> cateList = categoryDao.findAll();
            List<Category> ob = (List<Category>) (Object) cateList;

            ob.forEach((Object) -> {
                this.jCatalog.addItem(Object.getId() + "." + Object.getCategoryName());
            });

        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showTbExportId() throws SQLException {
        String id = txtSearchExport.getText();
        List<Export> exportListSearch = exportDao.search(id);

        tableEModel.setRowCount(0);
        exportListSearch.forEach((Object) -> {

            tableEModel.addRow(new Object[]{Object.getId(), Object.getPrice_export(),
                Object.getCreate_at()});
        });
    }

    public void showTbProduct() throws SQLException {

        List<Object> ProductList = productDao.findAll();
        List<Product> ob = (List<Product>) (Object) ProductList;

        tableModel.setRowCount(0);
        ob.forEach((Object) -> {

            tableModel.addRow(new Object[]{Object.getId(), Object.getProductName(),
                Object.getCatalogId(), Object.getPrice(), Object.getAmount(), Object.getCreateAt()});
        });
    }

    public void showTbProductIn() throws SQLException {

        List<Object> ProductList = productDao.findAll();
        List<Product> ob = (List<Product>) (Object) ProductList;

        tablePIModel.setRowCount(0);
        ob.forEach((Object) -> {

            tablePIModel.addRow(new Object[]{Object.getId(), Object.getProductName(), Object.getAmount(), Object.getPrice()});
        });
    }

    public void showTbStaff() throws SQLException {

        List<Object> staffList = staffDao.findAll();
        List<Staff> ob = (List<Staff>) (Object) staffList;

        tableModelStaff.setRowCount(0);
        ob.forEach((Object) -> {

            tableModelStaff.addRow(new Object[]{Object.getId(), Object.getStaffName(),
                Object.getBirthDay(), Object.getAddress(), Object.getCreatAt(), Object.getPhone()});
        });
    }

    public void showTbCategory() throws SQLException {

        List<Object> cateList = categoryDao.findAll();
        List<Category> ob = (List<Category>) (Object) cateList;

        tableModelCate.setRowCount(0);
        ob.forEach((Object) -> {

            tableModelCate.addRow(new Object[]{Object.getId(), Object.getCategoryName(), Object.getCreateAt()});
        });
    }

    public void showTbExport() throws SQLException {

        List<Object> ExportList = exportDao.findAll();
        List<Export> ob = (List<Export>) (Object) ExportList;

        tableEModel.setRowCount(0);
        ob.forEach((Object) -> {

            tableEModel.addRow(new Object[]{Object.getId(), Object.getPrice_export(),
                Object.getCreate_at()});
        });
    }

    public void showTbImport() throws SQLException {

        List<Object> ImportList = importDao.findAll();
        List<Import> ob = (List<Import>) (Object) ImportList;

        tableModelImport.setRowCount(0);
        ob.forEach((Object) -> {

            tableModelImport.addRow(new Object[]{Object.getId(), Object.getPrice_import(),
                Object.getCreate_at(), Object.getStaffId()});
        });
    }

    public void showSearchTbProduct() throws SQLException {

        String id = this.txtSearchProduct.getText();
        List<Product> productList = productDao.search(id);

        tableModel.setRowCount(0);
        productList.forEach((Object) -> {

            tableModel.addRow(new Object[]{Object.getId(), Object.getProductName(),
                Object.getCatalogId(), Object.getPrice(), Object.getAmount(), Object.getCreateAt()});
        });
    }

    public void showTbSearchProduct() throws SQLException {

        String id = this.txtSearchProduct1.getText();
        List<Product> productList = productDao.search(id);

        tableSearchModel.setRowCount(0);
        productList.forEach((Object) -> {

            tableSearchModel.addRow(new Object[]{Object.getId(), Object.getProductName(), Object.getAmount(), Object.getPrice()});
        });
    }

    public void showTbSearchProductIn() throws SQLException {

        String id = this.txtSearchProduct4.getText();
        List<Product> productList = productDao.search(id);

        tablePIModel.setRowCount(0);
        productList.forEach((Object) -> {

            tablePIModel.addRow(new Object[]{Object.getId(), Object.getProductName(), Object.getAmount(), Object.getPrice()});
        });
    }

//        tableModel.setRowCount(0);
//        Product.forEach((Food) -> {
//            tableModel.addRow(new Object[]{Food.getId(), Food.getName(),
//                Food.getCatalog(), Food.getPrice(), Food.getStatus()});
//        });
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JTableNV = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProduct = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lbId = new javax.swing.JLabel();
        jCatalog = new javax.swing.JComboBox<>();
        btnReset = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchProduct = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbCategory = new javax.swing.JTable();
        jPanelCate = new javax.swing.JPanel();
        addCate = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCategoryName = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        updateCate = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        lbCategoryId = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbStaff = new javax.swing.JTable();
        JPaneNV = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtStaffName = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        txtAddressStaff = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        txtPhone = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        lbStaffId = new javax.swing.JLabel();
        dateStaff = new com.toedter.calendar.JDateChooser();
        txtPosition = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtAcc = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        txtIdStaff = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        txtSearchProduct4 = new javax.swing.JTextField();
        searchPIn = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbShowP2 = new javax.swing.JTable();
        txtQuantityW3 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        btnAddP2 = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        txtPName2 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txtPId2 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtPAmount2 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        txtPPrice2 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtQuantityW2 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel63 = new javax.swing.JLabel();
        txtTotal2 = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        btnPay2 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbSearchProduct2 = new javax.swing.JTable();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbImport = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbImportDt = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtQtyIP = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        updateIP = new javax.swing.JButton();
        IPId = new javax.swing.JLabel();
        expIP = new com.toedter.calendar.JDateChooser();
        msgIP = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtSearchProduct1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbShowP = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        btnAddP = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        txtPName = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtPId = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtPAmount = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtPPrice = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtQuantityW = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        txtPQuantity = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        txtMoneyGuest = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtMoneyExtra = new javax.swing.JTextField();
        btnPay = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lbStaffName = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbSearchProduct = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtSearchExport = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbExportDetail = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbExport = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbStatProduct = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbStat = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        dateForm = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dateTo = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tbProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Giá bán", "Số lượng", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbProduct);

        btnAdd.setText("Thêm");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã sản phẩm");

        jLabel4.setText("Tên sản phẩm");

        jLabel5.setText("Loại sản phẩm");

        txtProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductNameActionPerformed(evt);
            }
        });

        jLabel6.setText("Giá bán");

        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jCatalog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCatalogActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel42.setText("Số lượng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addComponent(jSeparator2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtProductName, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                            .addComponent(lbId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jCatalog, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(15, 15, 15)))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtPrice)
                                            .addComponent(txtAmount)))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addComponent(btnReset)
                        .addGap(0, 893, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(lbId, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jCatalog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnReset))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Tìm kiếm");

        jButton20.setText("Tìm kiếm");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton20)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1252, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 808, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(353, 353, 353))
        );

        JTableNV.addTab("Sản phẩm", jPanel2);

        tbCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã loại sản phẩm", "Loại sản phẩm", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCategoryMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbCategory);

        addCate.setText("Thêm");
        addCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCateActionPerformed(evt);
            }
        });

        jLabel8.setText("Mã loại sản phẩm");

        jLabel9.setText("Loại sản phẩm");

        updateCate.setText("Sửa");
        updateCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCateActionPerformed(evt);
            }
        });

        jButton7.setText("Xóa");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCateLayout = new javax.swing.GroupLayout(jPanelCate);
        jPanelCate.setLayout(jPanelCateLayout);
        jPanelCateLayout.setHorizontalGroup(
            jPanelCateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCateLayout.createSequentialGroup()
                .addGroup(jPanelCateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCateLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3))
                    .addComponent(jSeparator4)
                    .addGroup(jPanelCateLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanelCateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCateLayout.createSequentialGroup()
                                .addComponent(addCate, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updateCate, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCateLayout.createSequentialGroup()
                                .addGroup(jPanelCateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCategoryName, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(lbCategoryId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 984, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelCateLayout.setVerticalGroup(
            jPanelCateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelCateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbCategoryId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(jPanelCateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCate)
                    .addComponent(updateCate)
                    .addComponent(jButton7))
                .addGap(32, 32, 32)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1310, Short.MAX_VALUE)
                    .addComponent(jPanelCate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelCate, javax.swing.GroupLayout.PREFERRED_SIZE, 139, Short.MAX_VALUE)
                .addGap(536, 536, 536))
        );

        JTableNV.addTab("Loại sản phẩm", jPanel6);

        tbStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Địa chỉ", "Ngày vào làm", "SĐT", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbStaffMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbStaff);

        jButton17.setText("Thêm");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel19.setText("Mã nhân viên");

        jLabel26.setText("Tên nhân viên");

        jLabel34.setText("Ngày sinh");

        jLabel35.setText("Địa chỉ");

        jButton18.setText("Sửa");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Xóa");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel37.setText("SĐT");

        dateStaff.setDateFormatString("dd-MM-yyyy");

        jLabel13.setText("Vị trí");

        jLabel14.setText("Tài khoản");

        jLabel23.setText("Mật khẩu");

        jButton5.setText("Thêm");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        txtIdStaff.setText("Mã nhân viên");

        jLabel53.setForeground(new java.awt.Color(255, 0, 51));
        jLabel53.setText("Thêm nhân viên");

        jLabel54.setForeground(new java.awt.Color(255, 0, 51));
        jLabel54.setText("Thêm tài khoản nhân viên");

        javax.swing.GroupLayout JPaneNVLayout = new javax.swing.GroupLayout(JPaneNV);
        JPaneNV.setLayout(JPaneNVLayout);
        JPaneNVLayout.setHorizontalGroup(
            JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPaneNVLayout.createSequentialGroup()
                .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPaneNVLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator11))
                    .addGroup(JPaneNVLayout.createSequentialGroup()
                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(JPaneNVLayout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPaneNVLayout.createSequentialGroup()
                                .addContainerGap(42, Short.MAX_VALUE)
                                .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbStaffId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(JPaneNVLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtPosition, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtAddressStaff, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(JPaneNVLayout.createSequentialGroup()
                                .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPaneNVLayout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(21, 21, 21))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPaneNVLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPaneNVLayout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPaneNVLayout.createSequentialGroup()
                                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(JPaneNVLayout.createSequentialGroup()
                                                .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtAcc, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                                    .addComponent(txtPass))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton5)
                                                .addGap(0, 465, Short.MAX_VALUE))
                                            .addComponent(jSeparator12)))
                                    .addGroup(JPaneNVLayout.createSequentialGroup()
                                        .addComponent(txtIdStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPaneNVLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(688, 688, 688)))))
                .addContainerGap())
        );
        JPaneNVLayout.setVerticalGroup(
            JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPaneNVLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPaneNVLayout.createSequentialGroup()
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(JPaneNVLayout.createSequentialGroup()
                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbStaffId, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdStaff)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txtStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(txtAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(dateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddressStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))))
                .addGroup(JPaneNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17)
                    .addComponent(jButton18)
                    .addComponent(jButton19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1322, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JPaneNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JPaneNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(481, Short.MAX_VALUE))
        );

        JTableNV.addTab("Nhân viên", jPanel15);

        jLabel56.setText("Tìm kiếm");

        searchPIn.setText("Tìm kiếm");
        searchPIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchProduct4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPIn)
                .addContainerGap(416, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(txtSearchProduct4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPIn))
                .addContainerGap())
        );

        tbShowP2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng trong kho", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane13.setViewportView(tbShowP2);
        if (tbShowP2.getColumnModel().getColumnCount() > 0) {
            tbShowP2.getColumnModel().getColumn(0).setResizable(false);
            tbShowP2.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton25.setText("Sửa");

        jButton26.setText("Xóa");

        btnAddP2.setText("Thêm");
        btnAddP2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddP2MouseClicked(evt);
            }
        });
        btnAddP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddP2ActionPerformed(evt);
            }
        });

        jLabel57.setText("Tên sản phẩm");

        txtPName2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPName2ActionPerformed(evt);
            }
        });

        jLabel58.setText("Mã sản phẩm");

        jLabel60.setText("Số lượng");

        jLabel61.setText("Đơn giá");

        jLabel62.setText("Số lượng trong kho");

        jButton6.setText("Reset");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout txtQuantityW3Layout = new javax.swing.GroupLayout(txtQuantityW3);
        txtQuantityW3.setLayout(txtQuantityW3Layout);
        txtQuantityW3Layout.setHorizontalGroup(
            txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtQuantityW3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtQuantityW3Layout.createSequentialGroup()
                        .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(txtQuantityW3Layout.createSequentialGroup()
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtQuantityW3Layout.createSequentialGroup()
                                .addGap(0, 38, Short.MAX_VALUE)
                                .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPId2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtQuantityW2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPPrice2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addComponent(txtPName2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPAmount2, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(txtQuantityW3Layout.createSequentialGroup()
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnAddP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton6))
                .addContainerGap())
        );
        txtQuantityW3Layout.setVerticalGroup(
            txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtQuantityW3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddP2)
                    .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel59)
                        .addComponent(txtPId2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addGap(4, 4, 4)
                .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel62)
                        .addComponent(txtQuantityW2)
                        .addComponent(jButton25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel60)
                    .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPAmount2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61)
                    .addGroup(txtQuantityW3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPPrice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton26)))
                .addGap(24, 24, 24))
        );

        jLabel63.setText("Tổng tiền");

        btnPay2.setText("Tạo phiếu yêu cầu");
        btnPay2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPay2MouseClicked(evt);
            }
        });
        btnPay2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPay2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator17, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
                    .addComponent(jSeparator18))
                .addContainerGap())
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(txtTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btnPay2)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(txtTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPay2)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        tbSearchProduct2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSearchProduct2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSearchProduct2MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tbSearchProduct2);

        jLabel66.setForeground(new java.awt.Color(255, 51, 51));
        jLabel66.setText("Sản phẩm yêu cầu");

        jLabel67.setForeground(new java.awt.Color(255, 0, 51));
        jLabel67.setText("Bảng sản phẩm ");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(91, 91, 91))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel67)
                            .addComponent(jScrollPane14))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQuantityW3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtQuantityW3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(316, 316, 316))))
        );

        JTableNV.addTab("Phiếu yêu cầu", jPanel23);

        tbImport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã số phiếu", "Giá nhập", "Ngày nhập", "Người tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbImport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbImportMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbImport);

        tbImportDt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Mã số phiếu", "Sản phẩm", "Số lượng yêu cầu", "Số lượng ", "Giá nhập", "NXS", "HSD", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbImportDt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbImportDtMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbImportDt);
        if (tbImportDt.getColumnModel().getColumnCount() > 0) {
            tbImportDt.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Chi tiết phiếu nhập");

        jLabel27.setForeground(new java.awt.Color(255, 51, 51));
        jLabel27.setText("Phiếu nhập hàng");

        jLabel15.setText("Số lượng");

        jLabel16.setText("NSX");

        jLabel17.setText("HSD");

        updateIP.setText("Cập nhật");
        updateIP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateIPMouseClicked(evt);
            }
        });
        updateIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateIPActionPerformed(evt);
            }
        });

        IPId.setText("Mã số phiếu");

        expIP.setDateFormatString("dd-MM-yyyy");

        msgIP.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jSeparator8)
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateIP, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(msgIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQtyIP, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                .addComponent(expIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(IPId))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(IPId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQtyIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(51, 51, 51)
                                .addComponent(updateIP)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(expIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(msgIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel27))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(390, 390, 390))
        );

        JTableNV.addTab("Phiếu nhập hàng", jPanel3);

        jLabel7.setText("Tìm kiếm");

        txtSearchProduct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchProduct1ActionPerformed(evt);
            }
        });

        jButton2.setText("Tìm kiếm");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(405, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSearchProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        tbShowP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng trong kho", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbShowP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbShowPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbShowP);
        if (tbShowP.getColumnModel().getColumnCount() > 0) {
            tbShowP.getColumnModel().getColumn(0).setResizable(false);
            tbShowP.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton1.setText("Sửa");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton15.setText("Xóa");
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
        });

        btnAddP.setText("Thêm");
        btnAddP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddPMouseClicked(evt);
            }
        });

        jLabel38.setText("Tên sản phẩm");

        txtPName.setEditable(false);
        txtPName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPNameActionPerformed(evt);
            }
        });

        jLabel18.setText("Mã sản phẩm");

        jLabel41.setText("Số lượng");

        jLabel40.setText("Đơn giá");

        txtPPrice.setEditable(false);

        jLabel22.setText("Số lượng trong kho");

        jButton9.setText("Reset");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addGap(0, 38, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(77, 77, 77)
                                        .addComponent(txtQuantityW, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtPPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addComponent(txtPName, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnAddP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddP)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel39)
                        .addComponent(txtPId, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(txtQuantityW)
                        .addComponent(jButton1))
                    .addComponent(txtPQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton15)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel20.setText("Tổng tiền");

        jLabel24.setText("Tiền khách đưa");

        jLabel25.setText("Tiền thừa trả khách ");

        btnPay.setText("Thanh toán");
        btnPay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPayMouseClicked(evt);
            }
        });
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        jLabel10.setText("Nhân viên thanh toán");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(btnPay))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbStaffName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMoneyGuest, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))
                .addGap(0, 91, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator10))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMoneyExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtMoneyGuest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMoneyExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnPay)
                .addContainerGap())
        );

        tbSearchProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSearchProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSearchProductMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbSearchProduct);

        jLabel32.setForeground(new java.awt.Color(255, 51, 51));
        jLabel32.setText("Sản phẩm thanh toán");

        jLabel33.setForeground(new java.awt.Color(255, 0, 51));
        jLabel33.setText("Bảng tìm kiếm ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(504, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(81, 81, 81))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel33)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE))
                        .addGap(12, 12, 12)))
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))))
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(289, 289, 289))
        );

        JTableNV.addTab("Thanh toán", jPanel4);

        jLabel21.setText("Tìm kiếm");

        txtSearchExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchExportActionPerformed(evt);
            }
        });

        jButton3.setText("Tìm kiếm");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchExport, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(410, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtSearchExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        tbExportDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã chi tiết đơn", "Mã đơn", "Mã sản phẩm", "Số lượng", "Thời gian"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tbExportDetail);
        if (tbExportDetail.getColumnModel().getColumnCount() > 0) {
            tbExportDetail.getColumnModel().getColumn(0).setHeaderValue("Mã chi tiết đơn");
            tbExportDetail.getColumnModel().getColumn(1).setHeaderValue("Mã đơn");
            tbExportDetail.getColumnModel().getColumn(4).setHeaderValue("Thời gian");
        }

        tbExport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Thành tiền", "Thời gian"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbExportMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbExport);
        if (tbExport.getColumnModel().getColumnCount() > 0) {
            tbExport.getColumnModel().getColumn(2).setHeaderValue("Thời gian");
        }

        jLabel49.setForeground(new java.awt.Color(255, 51, 51));
        jLabel49.setText("Bảng chi tiết hóa đơn ");

        jLabel50.setForeground(new java.awt.Color(255, 0, 51));
        jLabel50.setText("Bảng hóa đơn ");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(546, 546, 546))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel50)
                    .addComponent(jScrollPane9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel50)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(260, 260, 260))
        );

        JTableNV.addTab("Quản lý hóa đơn", jPanel11);

        tbStatProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tbStatProduct);

        tbStat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ngày", "Doanh Thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbStat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbStatMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tbStat);

        jLabel51.setForeground(new java.awt.Color(255, 51, 51));
        jLabel51.setText("Sản phẩm bán chạy");

        jLabel52.setForeground(new java.awt.Color(255, 0, 51));
        jLabel52.setText("Bảng thống kê");

        dateForm.setDateFormatString("dd-MM-yyyy");

        jLabel11.setText("Từ ngày");

        jLabel12.setText("Đến ngày");

        dateTo.setDateFormatString("dd-MM-yyyy");

        jButton4.setText("Tra cứu");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton8.setText("Làm mới");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateForm, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addGap(534, 534, 534))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jScrollPane11)
                        .addContainerGap())
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12)
                            .addComponent(jLabel51)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(508, 508, 508)
                                .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(491, 491, 491)))
                        .addGap(149, 149, 149))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel11))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton4)
                                    .addComponent(jButton8)))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addGap(367, 367, 367))
        );

        JTableNV.addTab("Thống kê", jPanel18);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JTableNV, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(JTableNV, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbSearchProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSearchProductMouseClicked
        // TODO add your handling code here:
        int index = tbSearchProduct.getSelectedRow();
        String id = tableSearchModel.getValueAt(index, 0).toString();
        String productName = tableSearchModel.getValueAt(index, 1).toString();
        String amount = tableSearchModel.getValueAt(index, 2).toString();
        String price = tableSearchModel.getValueAt(index, 3).toString();

        this.txtPId.setText(id);
        this.txtPName.setText(productName);
        this.txtPQuantity.setText(amount);
        this.txtPPrice.setText(price);
    }//GEN-LAST:event_tbSearchProductMouseClicked

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPayActionPerformed

    private void btnPayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPayMouseClicked
        // TODO add your handling code here:
        int moneyGuest = Integer.parseInt(txtMoneyGuest.getText());
        int total = Integer.parseInt(txtTotal.getText());
        int moneyExtra = moneyGuest - total;
        Account account = Account.getInstance(0, "", 0);
        int staffId = account.staffId;
        if (moneyExtra >= 0) {
            txtMoneyExtra.setText(String.valueOf(moneyExtra));
            Export export = new Export(total, staffId);

            try {

                for (int i = 0; i < tablePModel.getRowCount(); i++) {
                    int id = Integer.parseInt(tablePModel.getValueAt(i, 0).toString());
                    String productN = tablePModel.getValueAt(i, 1).toString();
                    int quantityQ = Integer.parseInt(tablePModel.getValueAt(i, 2).toString());
                    int quantity = Integer.parseInt(tablePModel.getValueAt(i, 3).toString());
                    int price = Integer.parseInt(tablePModel.getValueAt(i, 4).toString());
                    //                    List<Product> productQuantity = productDao.getQuantityById(id);
                    //                    int quantityQ = productQuantity.get(i-1).getAmount();
                    int quantityP = quantityQ - quantity;
                    Product productQ = new Product(id, productN, price, quantityP);

                    productDao.update(productQ);
                    showTbProduct();

                    int export_id = 0;
                    for (int x = 0; x < exportDao.findAll().size() + 1; x++) {
                        export_id += 1;
                    }
                    ExportDetail exportDt = new ExportDetail(export_id, id, quantity);
                    exportDtDao.insert(exportDt);
                }
                exportDao.insert(export);
                JOptionPane.showMessageDialog(rootPane, "Thanh toán thành công!");
                showTbExport();
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Can tra them " + (total - moneyGuest));
        }
    }//GEN-LAST:event_btnPayMouseClicked

    private void txtPNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPNameActionPerformed

    private void btnAddPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddPMouseClicked
        // TODO add your handling code here:
        if (txtPName.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap ten");
        } else if (txtPPrice.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap giá");
        } else {
            int id = Integer.parseInt(txtPId.getText());
            String productName = txtPName.getText();
            int amount = Integer.parseInt(txtPAmount.getText());
            int quantityQ = Integer.parseInt(txtPQuantity.getText());
            int price = Integer.parseInt(txtPPrice.getText());
            int total = amount * price;

            Object[] row = {id, productName, quantityQ, amount, price, total};
            int index = tbSearchProduct.getSelectedRow();

            if ((((int) tableSearchModel.getValueAt(index, 2)) - amount) >= 0) {
                tablePModel.addRow(row);
                int getTotal = 0;
                for (int i1 = 0; i1 < tablePModel.getRowCount(); i1++) {
                    getTotal += (int) tablePModel.getValueAt(i1, 5);
                }
                txtTotal.setText(String.valueOf(getTotal));
            } else {
                JOptionPane.showMessageDialog(rootPane, "So luong khong du!");
            }
        }

    }//GEN-LAST:event_btnAddPMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        if (txtSearchProduct1.getText().isEmpty() || txtSearchProduct1.getText().equals("")) {
            try {
                showTbProduct();
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else try {
            if (productDao.search(this.txtSearchProduct1.getText()).isEmpty()) {
                showTbProduct();
            } else {
                try {
                    // TODO add your handling code here:
                    showTbSearchProduct();
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void txtSearchProduct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchProduct1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchProduct1ActionPerformed

    private void tbSearchProduct2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSearchProduct2MouseClicked
        // TODO add your handling code here:
        int index = tbSearchProduct2.getSelectedRow();
        String id = tablePIModel.getValueAt(index, 0).toString();
        String productName = tablePIModel.getValueAt(index, 1).toString();
        String amount = tablePIModel.getValueAt(index, 2).toString();
        String price = tablePIModel.getValueAt(index, 3).toString();

        this.txtPId2.setText(id);
        this.txtPName2.setText(productName);
        this.txtQuantity.setText(amount);
        this.txtPPrice2.setText(price);
    }//GEN-LAST:event_tbSearchProduct2MouseClicked

    private void btnPay2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPay2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPay2ActionPerformed

    private void btnPay2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPay2MouseClicked
        // TODO add your handling code here:
        int total = Integer.parseInt(txtTotal2.getText());
        Account account = Account.getInstance(0, "", 0);
        int staffId = account.staffId;

        Import import2 = new Import(total, staffId);
        try {

            for (int i = 0; i < tablePI2Model.getRowCount(); i++) {
                int id = Integer.parseInt(tablePI2Model.getValueAt(i, 0).toString());
                String productN = tablePI2Model.getValueAt(i, 1).toString();
                int quantityQ = Integer.parseInt(tablePI2Model.getValueAt(i, 2).toString());
                int quantity = Integer.parseInt(tablePI2Model.getValueAt(i, 3).toString());
                int price = Integer.parseInt(tablePI2Model.getValueAt(i, 4).toString());

                int import_id = 0;
                for (int x = 0; x < importDao.findAll().size() + 1; x++) {
                    import_id += 1;
                }
                ImportDetail importDt = new ImportDetail(import_id, id, quantity, price, 1);
                importDtDao.insert(importDt);
            }
            importDao.insert(import2);
            showTbImport();
            JOptionPane.showMessageDialog(rootPane, "Thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPay2MouseClicked

    private void searchPInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPInActionPerformed
        // TODO add your handling code here:
        if (txtSearchProduct4.getText().isEmpty() || txtSearchProduct4.getText().equals("")) {

            try {
                showTbProductIn();
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                // TODO add your handling code here:
                showTbSearchProductIn();
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_searchPInActionPerformed

    private void tbExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbExportMouseClicked
        // TODO add your handling code here:
        try {

            showTbExportDT();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbExportMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        if (txtSearchExport.getText().isEmpty() || txtSearchExport.getText().equals("")) {
            try {
                showTbExport();
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else try {
            if (exportDao.search(this.txtSearchExport.getText()).isEmpty()) {
                showTbExport();
            } else {
                try {
                    // TODO add your handling code here:
                    showTbExportId();
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void txtSearchExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchExportActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSearchExportActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        Staff staff = new Staff();
        staff.setId(Integer.parseInt(lbStaffId.getText()));

        try {
            staffDao.delete(staff);

        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            showTbStaff();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.lbStaffId.setText("");
        this.txtStaffName.setText("");
        this.txtAddressStaff.setText("");
        this.txtPhone.setText("");
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(dateStaff.getDate());
        Staff staff = new Staff(Integer.parseInt(lbStaffId.getText()), txtStaffName.getText(), date, txtAddressStaff.getText(), txtPhone.getText());

        try {
            staffDao.update(staff);
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            showTbStaff();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(dateStaff.getDate());
        int position = Integer.parseInt(txtPosition.getText());
        Staff staff = new Staff(txtStaffName.getText(), date, txtAddressStaff.getText(), txtPhone.getText(), position);

        try {
            staffDao.insert(staff);
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            showTbStaff();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton17ActionPerformed

    private void tbStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStaffMouseClicked

        // TODO add your handling code here:
        int index = tbStaff.getSelectedRow();
        String id = tableModelStaff.getValueAt(index, 0).toString();
        String staffName = tableModelStaff.getValueAt(index, 1).toString();
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse((String) tableModelStaff.getValueAt(index, 2));
            this.dateStaff.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        String address = tableModelStaff.getValueAt(index, 3).toString();
        String phone = tableModelStaff.getValueAt(index, 5).toString();
        this.lbStaffId.setText(id);
        this.txtStaffName.setText(staffName);
        this.txtAddressStaff.setText(address);
        this.txtPhone.setText(phone);
        this.txtIdStaff.setText(id);
    }//GEN-LAST:event_tbStaffMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        try {
            // TODO add your handling code here:

            // TODO add your handling code here:
            showTbProduct();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbId.setText("");
        txtPrice.setText("");
        txtProductName.setText("");
        txtAmount.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        if (txtSearchProduct.getText().isEmpty() || txtSearchProduct.getText().equals("")) {
            try {
                showTbProduct();
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else try {
            if (productDao.search(this.txtSearchProduct.getText()).isEmpty()) {
                showTbProduct();
            } else {
                try {
                    // TODO add your handling code here:
                    showSearchTbProduct();
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jCatalogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCatalogActionPerformed

    }//GEN-LAST:event_jCatalogActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        Product product = new Product();
        product.setId(Integer.parseInt(lbId.getText()));

        try {
            productDao.delete(product);
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            showTbProduct();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (txtProductName.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap ten");
        } else if (txtPrice.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap price");
        } else {
            int price = Integer.parseInt(txtPrice.getText());
            int id = Integer.parseInt(lbId.getText());
            int amount = Integer.parseInt(txtAmount.getText());
            int catalog = Integer.parseInt(stripNonDigits(this.jCatalog.getItemAt(jCatalog.getSelectedIndex()), 0, 10));
            Product product = new Product(id, txtProductName.getText(), catalog, price, amount);

            try {
                productDao.update(product);
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                showTbProduct();
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
        if (txtProductName.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap ten");
        } else if (txtPrice.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap gia");
        } else if (txtAmount.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap so luong");
        } else {
            String productName = txtProductName.getText();
            int price = Integer.parseInt(txtPrice.getText());
            int amount = Integer.parseInt(txtAmount.getText());
            int catalog = Integer.parseInt(stripNonDigits(this.jCatalog.getItemAt(jCatalog.getSelectedIndex()), 0, 10));
            Product product = new Product(productName, catalog, price, amount);

            try {
                productDao.insert(product);
                //
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                showTbProduct();
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnAddMouseClicked

    private void tbProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductMouseClicked
        // TODO add your handling code here:

        int index = tbProduct.getSelectedRow();
        String id = tableModel.getValueAt(index, 0).toString();
        String productName = tableModel.getValueAt(index, 1).toString();
        //        String catalogId = tableModel.getValueAt(index, 2).toString();
        String price = tableModel.getValueAt(index, 3).toString();
        String amount = tableModel.getValueAt(index, 4).toString();
        String catalogId = tableModel.getValueAt(index, 2).toString();
        //        String status = tableModel.getValueAt(index, 4).toString();

        this.lbId.setText(id);
        this.txtProductName.setText(productName);
        this.txtPrice.setText(price);
        this.txtAmount.setText(amount);
    }//GEN-LAST:event_tbProductMouseClicked

    private void tbStatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbStatMouseClicked
    public void showStat() throws SQLException {
        final String OLD_FORMAT = "dd-MM-yyyy";
        final String NEW_FORMAT = "yyyy-MM-dd";

        SimpleDateFormat sdf1 = new SimpleDateFormat(OLD_FORMAT);
        sdf1.applyPattern(NEW_FORMAT);
        String dateForm = sdf1.format(this.dateForm.getDate());

        SimpleDateFormat sdf2 = new SimpleDateFormat(OLD_FORMAT);
        sdf2.applyPattern(NEW_FORMAT);
        String dateTo = sdf2.format(this.dateTo.getDate());

        StatisticalDao s = new StatisticalDao();
        List<Export> exportList = s.getStat(dateForm, dateTo);

        tableModelStat.setRowCount(0);
        exportList.forEach((Object) -> {
            tableModelStat.addRow(new Object[]{Object.getCreate_at(), Object.getPrice_export()});
        });

    }

    public void showStatProduct() throws SQLException {
        final String OLD_FORMAT = "dd-MM-yyyy";
        final String NEW_FORMAT = "yyyy-MM-dd";

        SimpleDateFormat sdf1 = new SimpleDateFormat(OLD_FORMAT);
        sdf1.applyPattern(NEW_FORMAT);
        String dateForm = sdf1.format(this.dateForm.getDate());

        SimpleDateFormat sdf2 = new SimpleDateFormat(OLD_FORMAT);
        sdf2.applyPattern(NEW_FORMAT);
        String dateTo = sdf2.format(this.dateTo.getDate());

        StatisticalDao s = new StatisticalDao();
        List<Product> productStatList = s.getStatProduct(dateForm, dateTo);

        tableModelStatProduct.setRowCount(0);
        productStatList.forEach((Object) -> {
            tableModelStatProduct.addRow(new Object[]{Object.getId(), Object.getProductName(), Object.getAmount()});
        });

    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // TODO add your handling code here:

            showStat();
            showStatProduct();

//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        String date = sdf.format(dateStaff.getDate());
//        Staff staff = new Staff(txtStaffName.getText(), date, txtAddressStaff.getText(), txtPhone.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton8ActionPerformed

    private void tbImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbImportMouseClicked
        // TODO add your handling code here:
        try {
            showTbImportDT();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbImportMouseClicked

    private void tbImportDtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbImportDtMouseClicked
        // TODO add your handling code here:
        int index = tbImportDt.getSelectedRow();
        String id = tableModelImportDetail.getValueAt(index, 0).toString();
        String quantity = tableModelImportDetail.getValueAt(index, 3).toString();
        this.IPId.setText(id);
        this.txtQtyIP.setText(quantity);
    }//GEN-LAST:event_tbImportDtMouseClicked

    private void updateIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateIPActionPerformed

    private void updateIPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateIPMouseClicked
        // TODO add your handling code here:
        int id = Integer.parseInt(IPId.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String msg = sdf.format(msgIP.getDate());
        String exp = sdf.format(expIP.getDate());
        int qty = Integer.parseInt(txtQtyIP.getText());
        ImportDetail importDt = new ImportDetail(id, qty, msg, exp, 1);

        try {
            int index = tbImportDt.getSelectedRow();
            int productId = (int) tableModelImportDetail.getValueAt(index, 2);
            int prl = productDao.getQuantityById(productId);
            int total = prl + qty;
            productDao.updateQ(total, productId);

            showTbProduct();
            importDtDao.update(importDt);
            JOptionPane.showMessageDialog(rootPane, "Thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            showTbImportDT();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateIPMouseClicked

    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
        // TODO add your handling code here:
        if (tbShowP.getSelectedRow() != -1) {
            // remove selected row from the model
            tablePModel.removeRow(tbShowP.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Xóa thành công!");
        }
    }//GEN-LAST:event_jButton15MouseClicked

    private void tbShowPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbShowPMouseClicked
        // TODO add your handling code here:
        int index = tbShowP.getSelectedRow();
        String id = tablePModel.getValueAt(index, 0).toString();
        String productName = tablePModel.getValueAt(index, 1).toString();
        String quantity = tablePModel.getValueAt(index, 2).toString();
        String amount = tablePModel.getValueAt(index, 3).toString();
        String price = tablePModel.getValueAt(index, 4).toString();

        this.txtPId.setText(id);
        this.txtPName.setText(productName);
        this.txtQuantityW.setText(quantity);
        this.txtPAmount.setText(amount);
        this.txtPPrice.setText(price);
    }//GEN-LAST:event_tbShowPMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        if (txtPName.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap ten");
        } else if (txtPPrice.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap giá");
        } else {
            int amount = Integer.parseInt(txtPAmount.getText());
            int quantityQ = Integer.parseInt(txtQuantityW.getText());
            int price = Integer.parseInt(txtPPrice.getText());
            int total = amount * price;

            int index = tbSearchProduct.getSelectedRow();

            if ((((int) tableSearchModel.getValueAt(index, 2)) - amount) >= 0) {
                tablePModel.setValueAt(amount, index, 3);
                tablePModel.setValueAt(total, index, 5);
                int getTotal = 0;
                for (int i1 = 0; i1 < tablePModel.getRowCount(); i1++) {
                    getTotal += (int) tablePModel.getValueAt(i1, 5);
                }
                txtTotal.setText(String.valueOf(getTotal));
            } else {
                JOptionPane.showMessageDialog(rootPane, "So luong khong du!");
            }
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void txtPName2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPName2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPName2ActionPerformed

    private void btnAddP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddP2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddP2ActionPerformed

    private void btnAddP2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddP2MouseClicked
        // TODO add your handling code here:
        if (txtPName2.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap ten");
        } else if (txtPPrice2.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap giá");
        } else {
            int id = Integer.parseInt(txtPId2.getText());
            String productName = txtPName2.getText();
            int amount = Integer.parseInt(txtPAmount2.getText());
            int quantityQ = Integer.parseInt(txtQuantity.getText());
            int price = Integer.parseInt(txtPPrice2.getText());
            int total = amount * price;
            int check = 0;

            Object[] row = {id, productName, quantityQ, amount, price, total};

            Product productQ = new Product(productName, 0, price, 0);

            tablePI2Model.addRow(row);
            int getTotal = 0;
            for (int i1 = 0; i1 < tablePI2Model.getRowCount(); i1++) {
                getTotal += (int) tablePI2Model.getValueAt(i1, 5);
            }
            txtTotal2.setText(String.valueOf(getTotal));

        }
    }//GEN-LAST:event_btnAddP2MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        if (txtAcc.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap tai khoan");
        } else if (txtPass.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap mat khau");
        } else {
            String acc = txtAcc.getText();
            String pass = txtPass.getText();
            int id = Integer.parseInt(txtIdStaff.getText());
            Staff staff = new Staff(acc, pass, id);
            try {
                staffDao.insertAcc(staff);
                JOptionPane.showMessageDialog(rootPane, "Them thanh cong!");
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Category category = new Category();
        category.setId(Integer.parseInt(lbCategoryId.getText()));

        try {
            categoryDao.delete(category);

        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            showTbCategory();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtCategoryName.setText("");
        lbCategoryId.setText("");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void updateCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCateActionPerformed
        // TODO add your handling code here:
        if (txtCategoryName.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap ten");
        } else {

            Category category = new Category(Integer.parseInt(lbCategoryId.getText()), txtCategoryName.getText());

            try {
                categoryDao.update(category);
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                showTbCategory();
                showCatalog();
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtCategoryName.setText("");
            lbCategoryId.setText("");
        }
    }//GEN-LAST:event_updateCateActionPerformed

    private void addCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCateActionPerformed
        // TODO add your handling code here:
        if (txtCategoryName.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chua nhap ten");
        } else {

            Category category = new Category(txtCategoryName.getText());

            try {
                categoryDao.insert(category);
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                showTbCategory();
                showCatalog();
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            txtCategoryName.setText("");
        }
    }//GEN-LAST:event_addCateActionPerformed

    private void tbCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCategoryMouseClicked
        // TODO add your handling code here:
        int index = tbCategory.getSelectedRow();
        String id = tableModelCate.getValueAt(index, 0).toString();
        String categoryName = tableModelCate.getValueAt(index, 1).toString();

        this.lbCategoryId.setText(id);
        this.txtCategoryName.setText(categoryName);
    }//GEN-LAST:event_tbCategoryMouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:

        txtPId2.setText("");
        txtPName2.setText("");
        txtQuantity.setText("");
        txtPAmount2.setText("");
        txtPPrice2.setText("");
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
        txtPId.setText("");
        txtPName.setText("");
        txtPQuantity.setText("");
        txtPAmount.setText("");
        txtPPrice.setText("");
    }//GEN-LAST:event_jButton9MouseClicked
    public void showTbImportDT() throws SQLException {
        int index = tbImport.getSelectedRow();
        int id = Integer.parseInt(tableModelImport.getValueAt(index, 0).toString());
        List<ImportDetail> ImportList = importDao.getById(id);
        List<ImportDetail> ob = (List<ImportDetail>) (Object) ImportList;

        tableModelImportDetail.setRowCount(0);
        ob.forEach((Object) -> {

            tableModelImportDetail.addRow(new Object[]{Object.getId(), Object.getImportId(), Object.getProductId(), Object.getQuantity(),
                Object.getQuantityImport(), Object.getPriceImport(), Object.getMsg(), Object.getExp(), Object.getStatus(), Object.getCreate_at()});
        });
    }

    public void showTbExportDT() throws SQLException {
        int index = tbExport.getSelectedRow();
        int id = Integer.parseInt(tableEModel.getValueAt(index, 0).toString());
        List<ExportDetail> ExportList = exportDao.getById(id);
        List<ExportDetail> ob = (List<ExportDetail>) (Object) ExportList;

        tableEDModel.setRowCount(0);
        ob.forEach((Object) -> {

            tableEDModel.addRow(new Object[]{Object.getId(), Object.getExportId(), Object.getProductId(), Object.getQuantity(),
                Object.getCreate_at()});
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    public static String stripNonDigits(final CharSequence input, int begin, int end) {
        final StringBuilder sb = new StringBuilder(input.length());
        if (end == 10) {
            end = input.length();
        }

        for (int i = begin; i < end; i++) {
            final char c = input.charAt(i);
            if (c > 47 && c < 58) {
                sb.append(c);

            }
        }
        return sb.toString();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IPId;
    private javax.swing.JPanel JPaneNV;
    private javax.swing.JTabbedPane JTableNV;
    private javax.swing.JButton addCate;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddP;
    private javax.swing.JButton btnAddP2;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnPay2;
    private javax.swing.JButton btnReset;
    private com.toedter.calendar.JDateChooser dateForm;
    private com.toedter.calendar.JDateChooser dateStaff;
    private com.toedter.calendar.JDateChooser dateTo;
    private com.toedter.calendar.JDateChooser expIP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jCatalog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelCate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lbCategoryId;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbStaffId;
    private javax.swing.JLabel lbStaffName;
    private com.toedter.calendar.JDateChooser msgIP;
    private javax.swing.JButton searchPIn;
    private javax.swing.JTable tbCategory;
    private javax.swing.JTable tbExport;
    private javax.swing.JTable tbExportDetail;
    private javax.swing.JTable tbImport;
    private javax.swing.JTable tbImportDt;
    private javax.swing.JTable tbProduct;
    private javax.swing.JTable tbSearchProduct;
    private javax.swing.JTable tbSearchProduct2;
    private javax.swing.JTable tbShowP;
    private javax.swing.JTable tbShowP2;
    private javax.swing.JTable tbStaff;
    private javax.swing.JTable tbStat;
    private javax.swing.JTable tbStatProduct;
    private javax.swing.JTextField txtAcc;
    private javax.swing.JTextField txtAddressStaff;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtCategoryName;
    private javax.swing.JLabel txtIdStaff;
    private javax.swing.JTextField txtMoneyExtra;
    private javax.swing.JTextField txtMoneyGuest;
    private javax.swing.JTextField txtPAmount;
    private javax.swing.JTextField txtPAmount2;
    private javax.swing.JLabel txtPId;
    private javax.swing.JLabel txtPId2;
    private javax.swing.JTextField txtPName;
    private javax.swing.JTextField txtPName2;
    private javax.swing.JTextField txtPPrice;
    private javax.swing.JTextField txtPPrice2;
    private javax.swing.JLabel txtPQuantity;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPosition;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtQtyIP;
    private javax.swing.JLabel txtQuantity;
    private javax.swing.JLabel txtQuantityW;
    private javax.swing.JLabel txtQuantityW2;
    private javax.swing.JPanel txtQuantityW3;
    private javax.swing.JTextField txtSearchExport;
    private javax.swing.JTextField txtSearchProduct;
    private javax.swing.JTextField txtSearchProduct1;
    private javax.swing.JTextField txtSearchProduct4;
    private javax.swing.JTextField txtStaffName;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotal2;
    private javax.swing.JButton updateCate;
    private javax.swing.JButton updateIP;
    // End of variables declaration//GEN-END:variables
}
