package userboard;

//DTO : ������ ��� �׸�, ������ ���ް�ü
//�ڷ����� Ŭ����
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
	
	public userDTO() { //�⺻������
		
	}
	
	public userDTO(String uname, String phone, String address) {
		//�Ű������� �ִ� ������
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
