# Integrasi Sistem

penting:
- Database (done)
- Controller
- UserInterface

### Class Staff
- lastName -> namaBelakang
- firstName -> namaDepan
- startWorkTime -> mulai_WK (WK=waktu kerja)
- finishWorkTime -> akhir_WK
- wageRate -> nominal_gaji
- state -> kondisi
- orderList[] -> daftarPesanan[]
- newID -> ID_baru
- newLastName -> namaBelakang_baru
- newFirstName -> namaDepan_baru
- newPassword -> password_baru
- setLastName() -> setNama_Belakang()
- setFirstName() -> setNama_Depan()
- setWorkState() -> setKondisi_Kerja()
- newState -> kondisi_baru
- getLastName() -> getNamaBelakang()
- getFirstName() -> getNamaDepan()
- getFullName() -> getNamaLengkap()
- fullName -> namaLengkap
- getWageRate() -> getNominal_Gaji()
- getStartTime() -> getWaktu_Mulai()
- getFinishTime() -> getWaktu_Selesai()
- getWorkState() -> getStatus_Kerja()
- clockIn() -> presensi()
- clockOut() -> absen()
- changeStartTime() -> ubahWaktu_Mulai()
- newStartTime -> waktuMulai_baru
- changeFinishTime() -> ubahWaktu_Selesai()
- newFinishTime() -> waktuSelesai_baru
- culculateWorkTime() -> hitung_WK() (WK=waktu kerja)
- diffTimeMin -> hitung_LK (LK=lama kerja)
- baseTime -> waktuInti
- fraction -> waktuSisa
- addTime -> tambahWaktu
- workTime -> waktuKerja
- setWageRate() -> setNominal_Gaji()
- newRate -> gajiBaru
- culculateWages() -> hitung_Gaji()

- WORKSTATE_NON_ACTIVE -> statusKerja_nonAktif
- WORKSTATE_ACTIVE -> statusKerja_Aktif
- WORKSTATE_FINISH -> statusKerja_Selesai


### Class Manager
- MINIMUM_RATE -> nominal_Minimum
- newID -> ID_baru
- newLastName -> namaBelakang_baru
- newFirstName -> namaDepan_baru
- newPassward -> password_baru
- newRate -> gaji_baru

### Class Employee -> 
- MINIMUM_RATE -> nominal_Minimum
- newID -> ID_baru
- newLastName -> namaBelakang_baru
- newFirstName -> namaDepan_baru
- newPassward -> password_baru
- newRate -> gaji_baru

### Class Order
- ORDER_CLOSED -> pesanan_dibayar
- ORDER_CANCELED -> pesanan_dibatalkan
- orderID -> ID_pesanan
- staffID -> ID_staff
- staffName -> namaStaff
- date -> tanggal
- state -> status
- orderDetailList -> List_rincianPesanan
- newStaffID -> IDstaff_baru
- newStaffName -> namaStaff_baru
- getOrderID() -> getID_pesanan()
- getStaffID() -> getID_staff
- getStaffName() -> getNamaStaff()
- getState() -> getStatus()
- getOrderDetail() -> getRincian_pesanan()
- setOrderID() -> setID_pesanan()
- newID -> ID_baru
- setState() -> setStatus()
- state -> status
- addItem() -> tambahItem()
- rNewMenuItem -> rMenuItem_baru
- quantity -> jumlah
- detail -> rincian
- deleteItem() -> hapusItem()
- calculateTotal() -> hitungTotal()

### Class OrderDetail
- itemID -> ID_item
- itemName -> namaItem
- price -> harga
- quantity -> jumlah
- totalPrice -> totalHarga
- newMenuItem -> menuItem_baru
- newQuantity -> jumlah_baru
- getItemID() -> getID_item()
- getItemName() -> getNama_item()
- getPrice() -> getHarga()
- getQuantity() -> getJumlah()
- getTotalPrice() -> getTotal_harga()
- addQuantity() -> tambahJumlah()

### Class MenuItem
- MAIN -> Makanan
- DRINK -> Minuman
- ALCOHOL -> Penyegar
- DESSERT -> Cuci_Mulut
- name -> nama
- type -> tipe
- price -> harga
- state -> status
- promotion_price -> harga_promo
- PROMOTION_ITEM -> item_promosi
- SEASONAL_ITEM -> item_khusus
- newID -> ID_baru
- newName -> nama_baru
- newPrice -> harga_baru
- newType -> tipe_baru
- setName() -> setNama()
- setPrice() -> setHarga()
- setType() -> setTipe()
- setState() -> setStatus()
- newState -> status_baru
- tempPrice -> tempHarga
- resetState -> resetStatus()
- getName() -> getNama()
- getPrice() -> getHarga()
- gerRegularPrice() -> getHargaBiasa()
- getType() -> getTipe()
- getState() -> getStatus()

### Class DB_Exception
- errMsg -> pesan_error
- msg -> pesan
- getErrMessage() -> getPesanError()

### Class Resto - clear

### Class Database
- STAFF_FILE -> DB_staff
- MANAGER_FILE -> DB_manager
- MENU_FILE -> DB_menu
- REPORT_FILE -> DB_nota
- PAYMENT_FILE -> DB_gaji
- WAGE_INFO_FILE -> DB_info_gaji

- staffList -> daftar_staff
- menuList -> daftar_menu
- orderList -> daftar_pesanan
- date -> tgl
- todaysOrderCounts -> hitung_pesanan_skrg
- getStaffList() -> getDaftar_staff()
- getMenuList() -> getDaftar_menu()
- getOrderList() -> getDaftar_pesanan()
- getTodaysOrderCount() -> getHitung_pesanan_skrg()
- findStaffByID() -> cari_IDstaff()
- found -> cek
- findMenuItemByID() -> cari_IDitem()
- findOrderByID() -> cari_IDpesanan()

- EDIT_LAST_NAME -> edit_namaBelakang
- EDIT_FIRST_NAME -> edit_namaDepan
- EDIT_PASSWORD -> edit_password

- editStaffData() -> edit_dataStaff()
- staffID -> ID_staff
- newPassword -> password_baru
- newLastName -> namaBelakang_baru
- newFirstName -> namaDepan_baru
- which -> kondisi
- newData -> data_baru
- deleteStaff() -> hapus_staff()
- addStaff() -> tambah_staff()
- isManager -> cek_manager
- newStaff -> staff_baru

- EDIT_ITEM_NAME -> edit_namaItem
- EDIT_ITEM_PRICE -> edit_hargaItem
- EDIT_ITEM_TYPE -> edit_tipeItem

- editMenuItemData() -> edit_data_menuItem()
- setMenuItemAsPromotionItem() -> setMenu_sbgPromosi()
- resetMenuState() -> reset_kondisiMenu()
- deleteMenuItem() -> hapus_menuItem()
- addMenuItem() -> tambah_menuItem()
- addOrder() -> tambah_pesanan()
- addOrderItem() -> tambah_itemPesanan()
- deleteOrderItem() -> hapus_itemPesanan()
- cancelOrder() -> batalkan_pesanan()
- deleteOrder() -> hapus_pesanan()
- closeOrder() -> bayar_pesanan()
- closeAllOrder() -> bayar_semuaPesanan()
- getOrderState() -> getKondisi_pesanan()
- getOrderTotalCharge() -> getBiaya_totalPesanan()
- checkIfAllOrderClosed() -> cek_semuaPesanan_dibayar()
- checkIfAllStaffCheckout() -> cek_semuaStaff_pulang()
- forthClockOutAllStaff() -> pulangkan_semuaStaff()

- loadFiles() -> load_DB()
- loadStaffFile() -> load_DB_staff()
- loadManagerFile() -> load_DB_manager()
- loadMenuFile() -> load_DB_menu()
- loadWageInfoFile() -> load_DB_gaji

- reader -> baca
- line -> baris
- writer -> tulis
- fileName -> namaFile
- updateWageFile() -> ubah_DB_gaji()
- updateStaffFile() -> ubah_DB_staff()
- generateOrderReport() -> cetak_nota_pesanan()
- todaysDate -> tgl_skrg
- state -> status
- totalAllOrder -> total_pesanan
- generateFileName -> cetak_namaFile
- orderCnt -> hitung_pesanan
- cancelCnt -> hitung_dibatalkan
- cancelTotal -> total_dibatalkan
- today -> hari_ini
- stateString -> status_string
- totalOfEachOrder -> total_tiapPesanan
- generatePaymentReport() -> cetak_pembayaran()
- totalPayment -> total_pembayaran
- generateFileName -> cetak_namaFile
- staffNum -> no_staff
- pay -> bayar


### Class Controller
- userType -> tipe_user
- currentUserID -> IDuser_skrg
- currentUserName -> user_skrg
- todaysDate -> tgl_skrg

- todaysOrderCnt -> hitung_pesanan_skrg
- totalSales -> total_penjualan
- todaysCancelCnt -> hitung_dibatalkan_skrg
- cancelTotal -> total_dibatalkan
- errorMessage -> pesan_error
- orderID -> ID_pesanan
- isManager -> cek_manager
- searchClassName -> cari_namaClass
- newID -> ID_baru
- newPassword -> password_baru
- newFirstName -> namaDepan_baru
- newLastName -> namaBelakang_baru
- newName -> nama_baru
- menuType -> tipe_menu
- addItemID -> tambah_IDitem
- addItemQuantity -> tambah_jumlahItem
- rNewItem -> rItem_baru
- deleteNo -> hapus_no
- closeOrderID -> close_IDpesanan
- cancelOrderID -> cancel_IDpesanan
- disuplayMenuType -> tampil_tipeMenu
- totalPayment -> total_pembayaran
- 