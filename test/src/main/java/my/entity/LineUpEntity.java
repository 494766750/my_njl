package my.entity;


import lombok.Data;

@Data
public class LineUpEntity implements Comparable{

    private String userCode;

    /**
     * 不区分等级
     *  A-xx 普通用户 V-xx vip用户
     */
    private String lineUpCode;

    public void setLineUpCode(String lineUpCode) {
        this.lineUpCode = lineUpCode;
        this.lineUpCodeArr = lineUpCode.split("-");
    }

    private String[] lineUpCodeArr;

    @Override
    public int compareTo(Object comparableEntity) {
        LineUpEntity ce = (LineUpEntity) comparableEntity;
        if(this.lineUpCodeArr[0].equals(ce.lineUpCodeArr[0])){
            return Integer.valueOf(this.lineUpCodeArr[1])-Integer.valueOf(ce.lineUpCodeArr[1]);
        }
        return this.lineUpCodeArr[0].compareTo(ce.lineUpCodeArr[0])*-1;
    }
}
