Author
	Group: Grasshopper
Nguyễn Thị Minh Ly - 23020399 
Đặng Minh Nguyệt - 23020407 
Trần Trung Hiếu - 22026513 

Description
Đây là ứng dụng được thiết kế để hỗ trợ học Ngoại ngữ. Lấy nguồn cảm hứng từ Châu chấu, team Grasshopper xây dựng ứng dụng xoay quanh linh vật Tepper.
Chúng tôi mong muốn người học và Tepper có sự tương tác trong quá trình học tập. Ứng dụng được viết bằng ngôn ngữ Java và thư viện JavaFX. 
Ứng dụng sử dụng E_V.txt, V_E.txt & BookmarkList.txt để lưu trữ dữ liệu từ điển, các tệp file .mp3 & .wav dùng cho âm thanh phát sẵn, API Google Translate & TextToSpeech Voice RSS cho tính năng dịch thuật và phát âm. 
Ứng dụng học tiếng Anh
Ngôn ngữ: Java, thư viện: JavaFx
Cơ sở dữ liệu: .txt, .wav, .mp3

UML

Demo

Installation
Clone ứng dụng từ repo này. 
Chạy ứng dùng từ class Main trong org.example.eapp 
Ứng dụng sử dụng database quản lý bởi SQL 

Usage
Dịch thuật: Tính năng dịch từ Anh-Việt & Việt-Anh, phát âm đoạn văn bản được dịch 
Từ điển: Tra cứu các từ trong Từ điển với hai chế độ Anh-Việt & Việt-Anh, phát âm từ được chọn và đánh dấu từ vào Bookmark 
Bookmark:
Danh sách các từ được đánh dấu. 
Chức năng Thêm-Sửa-Xóa trong Bookmark (Thêm từ vào Bookmark, Xóa và Sửa các từ đang có trong Bookmark - chức năng chỉ thực hiện với Bookmark không sửa vào từ điển gốc). 
Chức năng đặc biệt: “Slider cổ vũ”, bạn sẽ được cổ vũ khi kéo Slider với 
  một giá trị bất kỳ. 
Thư viện: Thư viện bao gồm những câu chuyện ngụ ngôn được chọn lọc, có tính năng nghe audio book (play, pause & return). Đồng thời, khi người dùng bôi đậm một đoạn văn bản, trang web sẽ load phần dịch tương ứng. Để hiện lại văn bản tiếng Anh, người dùng nhấn chuột phải, chọn ‘reload page’.
Game: 
Game HangMan: Đoán từ đúng dựa trên gợi ý có sẵn, người chơi có 6 lượt đoán. Mỗi khi đoán sai 1 chữ, hình ảnh người bí ngô bị treo cổ sẽ hiện ra. 
Game Cacti Hopper: Một phiên bản của thể loại game vượt chướng ngại vật. Người chơi đóng vai châu chấu Tepper và có nhiệm vụ trả lời các câu hỏi xoay quanh hai phần Đồng nghĩa & Trái nghĩa trong khi nhảy qua các cây xương rồng. Người chơi sẽ thắng khi đạt được 100 điểm, nếu trả lời sai hoặc đâm vào chướng ngại vật game sẽ kết thúc.
