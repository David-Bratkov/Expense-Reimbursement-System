package dto;

public class UserDTO {

    String fullname;
    Integer userId;
    Integer roleId;

    public UserDTO() {}

    public UserDTO(String fullname, Integer userId, Integer roleId) {
        this.fullname = fullname;
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "fullname='" + fullname + '\'' +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
