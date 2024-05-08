# AUTHOR
## 	Group: Grasshopper
**Nguyễn Thị Minh Ly** - 23020399

**Đặng Minh Nguyệt** - 23020407

**Trần Trung Hiếu** - 22026513

## Description
Đây là ứng dụng được thiết kế để hỗ trợ học Ngoại ngữ. Lấy nguồn cảm hứng từ Châu chấu, team **Grasshopper** xây dựng ứng dụng xoay quanh linh vật **Tepper**. Chúng tôi mong muốn người học và **Tepper** có sự tương tác trong quá trình học tập. Ứng dụng được viết bằng ngôn ngữ Java và thư viện JavaFX. Ứng dụng sử dụng E_V.txt, V_E.txt và BookmarkList.txt để lưu trữ dữ liệu từ điển, các tệp file .mp3 & .wav dùng cho âm thanh phát sẵn, dùng **API Google Translate** & **TextToSpeech Voice RSS**.

>Ứng dụng học Ngoại ngữ.
>
>Linh vật châu chấu **Tepper**.
>
>Ngôn ngữ: Java, thư viện: JavaFx
>
>Cơ sở dữ liệu: .txt, .wav, .mp3 (game **Cacti Hopper**)


## UML

![image](https://github.com/minh071289/Grasshopper/assets/146638068/0385100c-feea-477e-b302-702ee02f60a2)

## Installation

- Clone ứng dụng từ repo này.

- Chạy ứng dùng từ class Main trong **org.example.eapp**

## Usage
- **Dịch thuật**: Tính năng dịch từ **Anh-Việt** & **Việt-Anh**, phát âm đoạn văn bản được dịch 

- **Từ điển**: Tra cứu các từ trong Từ điển với hai chế độ Anh-Việt & Việt-Anh, phát âm từ được chọn và đánh dấu từ vào Bookmark.

- **Bookmark**: Danh sách các từ được đánh dấu. Chức năng Thêm-Sửa-Xóa trong Bookmark (Thêm từ vào Bookmark, Xóa và Sửa các từ đang có trong Bookmark. Chức năng chỉ thực hiện với Bookmark không sửa vào từ điển gốc).

- **Một chức năng đặc biệt**: “Slider cổ vũ”, bạn sẽ được cổ vũ khi kéo **Slider** với một giá trị bất kỳ.

- **Thư viện**: Thư viện bao gồm những câu chuyện ngụ ngôn được chọn lọc, có tính năng nghe audio book (play, pause & return). Đồng thời, khi người dùng bôi đậm một đoạn văn bản, trang web sẽ load phần dịch tương ứng.Để hiện lại văn bản tiếng Anh, người dùng nhấn chuột phải, chọn **‘reload page’**.

### Game: 

- **Game HangMan**: 

  Đoán từ đúng dựa trên gợi ý có sẵn, người chơi có 6 lượt đoán. Mỗi khi đoán sai 1 chữ, hình ảnh người bí ngô bị treo cổ sẽ hiện ra. 

- **Game Cacti Hopper**: 

Một phiên bản của thể loại game vượt chướng ngại vật. Người chơi đóng vai châu chấu **Tepper** và có nhiệm vụ trả lời các câu hỏi xoay quanh hai phần Đồng nghĩa & Trái nghĩa trong khi nhảy qua các cây xương rồng. Người chơi sẽ thắng khi đạt được **100 điểm**, nếu trả lời sai hoặc đâm vào chướng ngại vật game sẽ kết thúc.

## Demo
**1. Màn hình chính khi vào app**

![image](https://github.com/minh071289/Grasshopper/assets/146638068/5a3e510c-3194-401a-a266-5769fec2a71e)

**2. Màn hình Dịch thuật**

![image](https://github.com/minh071289/Grasshopper/assets/146638068/c81cb20e-988b-4184-a7b6-cef4d07dc75a)

**3. Từ điển**

![image](https://github.com/minh071289/Grasshopper/assets/146638068/dc652ea0-5cf7-458f-9757-091d1e7bd6d2)

**4. Màn hình Bookmark**

![image](https://github.com/minh071289/Grasshopper/assets/146638068/6d7eda8b-ce31-4c73-9114-215665b75369)


**5. Màn hình Thư viện**

![image](https://github.com/minh071289/Grasshopper/assets/146638068/f6eeacdb-6d11-4d03-be22-306b61602112)

**6. Màn hình Trung tâm Trò chơi**

**a, Game HangMan**

![image](https://github.com/minh071289/Grasshopper/assets/146638068/11f99f91-700d-4e05-b534-e0210daf6d1d)

**b, Game Cacti Hopper**

![image](https://github.com/minh071289/Grasshopper/assets/146638068/f295c73d-3b32-44aa-b967-a5bcab8f3a65)

**VideoDemo:** [https://drive.google.com/file/d/1jui7Zh7rFP_52BmkRn85HssH7T6jwv72/view?usp=sharing](url)

## Future Improvements

- Thêm cơ sở dữ liệu người dùng, thanh cài đặt.

**Dịch thuật:**
  
- Bổ sung thêm nhiều ngôn ngữ

- Phát hiện ngôn ngữ

- Chức năng dịch từ hình ảnh

**Từ điển:**

- Bổ sung giọng US-UK.

- Thêm nhiều từ hơn trong dữ liệu từ điển

- Cập nhật thêm **“Đề xuất từ mới”**

- Cập nhật lịch sử tra cứu gần đây

**Bookmark:**

- Tạo thêm chức năng tách riêng các Folder chủ đề ứng với các từ

- Thêm định dạng văn bản đối với các từ tự thêm

- Thư viện

- Bổ sung thêm nhiều đầu sách mới

- Nâng cấp chức năng audio

## Game:

- Nâng cấp cơ sở dữ liệu cho game, phân chia các cấp độ ứng với trình độ người học

- Thêm nhiều game thú vị hơn, thường xuyên bảo trì và lắng nghe phản hồi từ người dùng

## Project Status

**Dự án này đã hoàn thành**

## Notes

- Dự án được viết nhằm mục đích học tập & giải trí

- Thư viện được tham khảo từ trang **Aesop for Children**




