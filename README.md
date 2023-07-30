# JavaLibrary

這是一個用java做出的簡易圖書館系統，有連接資料庫以確保資料保存

視窗部分是使用 java swing 來製作

具備創建刪除帳戶、借還書及新增書籍功能

以下是各個檔案的解說

StartClass.java : 用來開啟圖書館首頁

HomePage.java : 圖書館首頁，有退出按鈕可以關閉整支程式

RegisterPage.java : 註冊畫面

UserPage.java : 一般使用者的UI介面，先從此頁點選需要的功能後就會跳轉到相關介面

AdminPage : 圖書管理員的使用者介面，可以新增、刪除、修改書籍資訊

BorrowBookPage.java : 使用者點選"借書"按鈕後會跳轉到此介面，提供查詢書籍及借書的功能

ReturnPage.java : 使用者點選"還書及借閱紀錄"按鈕會跳轉到此介面，此介面可以看到過往的借閱紀錄及可以進行還書的動作

PersonProfilePage.java : 使用者點選"個人資料"按鈕後會跳轉到此頁面，使用者可以在此介面修改個人資料

library.sql : 此SQL檔提供了圖書館系統所使用的資料庫結構，內含少許資料供測試使用，也可以自由新增所需資料
