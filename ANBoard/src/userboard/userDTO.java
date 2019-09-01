package userboard;

//DTO : 데이터 담는 그릇, 데이터 전달객체
//자료저장 클래스
public class userDTO {
	
	private int id;
	private String uname;
	private String phone;
	private String address;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUname() {
		return uname;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddr() {
		return address;
	}
	
	public void setAddr(String address) {
		this.address = address;
	}
	
	public userDTO() { //기본생성자
		
	}
	
	public userDTO(String uname, String phone, String address) {
		//매개변수가 있는 생성자
		super();
		this.uname = uname;
		this.phone = phone;
		this.address = address;
	}

	@Override
	public String toString() {
		return "userDTO [id=" + id + ", uname=" + uname + ", phone=" + phone + ", address=" + address + "]";
	}

	

}
