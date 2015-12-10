# Resto Joglo Manis (RJM)

## To-Do Development
- butuh build tools untuk export jadi file .jar
- butuh native-look UX (eye constrain)

## Login
penyimpanan data temporary,.. membaca DB dari /dataFiles

### Manager
ID,Pass,Nama_Depan,Nama_Blkng
- ID:1000 Password:admin
- ID:5555 Password:admin

### Staff
ID,Pass,Nama_Depan,Nama_Blkng
* ID:1111 Password:ihsan
* ID:3333 Password:agung  

(Ojo diutak atik formate cak. ndak rusak mengko)  


## Tampil menu
- untuk melihat semua item di menu [clik ALL]
- untuk melihat item berdasarkan kategori [click kategorinya]


## Atur Pesanan
### Buat Pesanan Baru
1. Click tombol "Show menu" yang ada di kiri
2. Click tombol "New" untuk membuat pesanan baru
3. Untuk memilih item click di bagian daftar menu yang ada di bagian kanan
4. Masukkan jumlah dan click "Add" di bagian kiri bawah.(jika jumlah=kosong, jumlah default=1)
5. Untuk menghapus pesanan, click item di daftar pesanan dan click tombol "Delete"


### Edit Pesanan
1. Click tombol "Show Menu" di bagian kiri
2. Pilih pesanan yang akan diubah di bagian daftar pesanan
3. Click tombol "Edit"
4. Bisa menambah atau menghapus pesanan

### Close / Cancel pesanan
1. Pilih pesanan dari bagian daftar pesanan
2. Click tombol "Close" atau tombol "Cancel"
3. Pesanan yang sudah di "Close" atau di "Cancel" tidak bisa di edit


## Atur Staff/Pekerja (Manager only)
### Tambah staff baru
1. Click tombol "Manage Employees" dibagian panel kiri
2. Click tombol "New"
3. Isi semua data staff dan click OK

### Edit staff
1. Click tombol "Manage Employees" dibagian panel kiri
2. Pilih staff yang akan di edit
3. Click tombol "Edit"
4. Isi(ubah) data staff yang ingin di edit -> click OK
   (ID staff tidak bisa diedit utk mempermudah proses sorting)

### Hapus staff
1. Click tombol "Manage Employees" dibagian panel kiri
2. Pilih staff yang akan di hapus
3. Click tombol "Delete"


##Atur Daftar Menu (Manager only)
### Tambah item baru
1. Click tombol "Manage menu items" dibagian panel kiri
2. Click tombol "Add new menu item"
3. Isi semua informasi tentang menu dan Click OK

### Edit item di menu
1. Click tombol "Manage menu items" dibagian panel kiri
2. Pilih menu yang akan di edit dari daftar menu
3. Click tombol "Edit menu item"
4. Isi semua informasi tentang menu dan Click OK
   (ID menu tidak bisa diedit utk mempermudah proses sorting)

### Hapus item di menu
1. Click "Manage menu items" Button on the left
2. Select a menu item from the menu list
3. Click "Delete menu item" button

1. Click tombol "Manage menu items" dibagian panel kiri
2. Pilih menu yang akan di hapus dari daftar menu
3. Click tombol "Delete menu item" 


## Pembayaran Pegawai/Staff
* Ketika pertama login, sistem akan secara otomatis mencatat record waktu mulai bekerja.
* Tombol "Clock out" akan mengakhiri waktu kerja orang yang sedang login.
* Manager dapat melakukan "Clocked out" lewat "Manage Employees", 
  dengan cara memilih staff dan meng-click tombol "Clok out"
* Manager dapat melihat history pembayaran hari ini,
  dengan cara meng-click tombol "Show Payment" di bagian panel kiri
  
