package himedia.Practice.TEST;

public enum PrintMenu {
    ADDMEMBER("회원추가",1),
    SELECTEMAIL("회원조회(이메일)",2),
    SELECTNAME("회원조회(이름)",3),
    SELECTALL("전체조회",4),
    UPDATEMEMBER("회원수정",5),
    DELETEMEMBER("회원삭제",6),
    EXIT("회원추가",7);


    // 필드
    private int description;

    PrintMenu(String description,int desc) {
        this.description = Integer.parseInt(description);
    }

    public int getDescription() {
        return description;
    }
}

