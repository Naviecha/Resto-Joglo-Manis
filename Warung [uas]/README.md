# Warung Bu Rochman (Manual Guide)

## To-Do Development
- butuh build tools untuk export jadi file .jar
- butuh native-look UX (eye constrain)

## Login
penyimpanan data temporary,.. membaca DB dari SQL

### Manager
ID, Password, Nama Depan, Nama Belakang
- ID:1000 Password:admin 	[ Bu Rohman ]
- ID:5555 Password:admin 	[ Ivan ]

### Staff
ID, Password, Nama Depan, Nama Belakang
* ID:1111 Password:intan	[ Mbak Intan ]
* ID:2222 Password:diana	[ Mbak Diana ]
* ID:3333 Password:supri  	[ Mas Supri ]


## Tampil menu
- untuk melihat semua item di menu [klik semua]
- untuk melihat item berdasarkan kategori [klik kategorinya]


## Atur Pesanan
### Buat Pesanan Baru
1. Klik tombol "Atur Pesanan" yang ada di kiri
2. Klik tombol "Pesan" untuk membuat pesanan baru
3. Untuk memilih item klik di bagian daftar menu yang ada di bagian kanan
4. Masukkan jumlah dan klik "Tambah" di bagian kiri bawah.(jika jumlah=kosong, jumlah default=1)
5. Untuk menghapus pesanan, klik item di daftar pesanan dan klik tombol "Hapus"


### Edit Pesanan
1. Klik tombol "Atur Pesanan" di bagian kiri
2. Pilih pesanan yang akan diubah di bagian daftar pesanan
3. Klik tombol "Edit"
4. Bisa menambah atau menghapus pesanan

### Bayar / Batalkan pesanan
1. Pilih pesanan dari bagian daftar pesanan
2. Klik tombol "Bayar" atau tombol "Batalkan"
3. Pesanan yang sudah di "Bayar" atau di "Batalkan" tidak bisa di edit


## Atur Staff/Pegawai (Manager only)
### Tambah staff baru
1. Klik tombol "Atur Pegawai" dibagian panel kiri
2. Klik tombol "Tambah staff"
3. Isi semua data staff dan klik OK

### Edit staff
1. Klik tombol "Atur Pegawai" dibagian panel kiri
2. Pilih staff yang akan di edit
3. Klik tombol "Edit staff"
4. Isi(ubah) data staff yang ingin di edit -> klik OK
   (ID staff tidak bisa diedit untuk mempermudah proses sorting)

### Hapus staff
1. Klik tombol "Atur Pegawai" dibagian panel kiri
2. Pilih staff yang akan di hapus
3. Klik tombol "Hapus Staff", Konfirmasi OK


##Atur Daftar Menu (Manager only)
### Tambah item baru
1. Klik tombol "Atur Daftar Menu" dibagian panel kiri
2. Klik tombol "Tambah menu"
3. Isi semua informasi tentang menu dan Klik OK

### Edit item di menu
1. Klik tombol "Atur Daftar Menu" dibagian panel kiri
2. Pilih menu yang akan di edit dari daftar menu
3. Klik tombol "Edit menu"
4. Isi semua informasi tentang menu dan Klik OK
   (ID menu tidak bisa diedit untuk mempermudah proses sorting)

### Hapus item di menu
1. Klik tombol "Atur Daftar Menu" dibagian panel kiri
2. Pilih menu yang akan di hapus dari daftar menu
3. Klik tombol "Hapus menu", Konfirmasi OK


## [History] Total Penjualan (Manager only)
1. Klik tombol "Total Penjualan" dibagian panel kiri
2. Manager dapat melihat history penjualan yang terjadi
3. Apabila ingin mencetak nota, klik tombol "Cetak nota" pada bagian kiri bawah


## Pembayaran Pegawai/Staff (Manager only)
* Ketika pertama login, sistem akan secara otomatis mencatat record waktu staff yg mulai bekerja.
* Tombol "Check out" akan mengakhiri waktu kerja staff yang sedang login.
* Manager dapat melakukan "Check out" lewat "Atur Pegawai", 
  dengan cara memilih staff dan meng-klik tombol "Check out"
* Manager dapat melihat biaya pembayaran gaji hari ini,
  dengan cara meng-klik tombol "Pembayaran" di bagian panel kiri
  
