package com.easysoft.core.model;

/**
 * User: andy
 * Date: 14-4-1
 * Time: 下午5:32
 *
 * @since:
 */
public class Birthday {
    private String birthday;

    public Birthday(String birthday) {

        this.birthday = birthday;
    }
    //setter、getter
    public Birthday() {}
    @Override
    public String toString() {
        return this.birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
