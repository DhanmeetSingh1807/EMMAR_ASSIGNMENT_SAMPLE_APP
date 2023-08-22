package com.example.emmar_assignment.ui.database.entity;

import static com.example.emmar_assignment.ui.utils.Utils.getDate;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import kotlinx.android.parcel.Parcelize;

/**
 * Created by Dhanmeet on 22/08/23.
 */
// Table for storing user Info
@Entity(tableName = "User", indices = @Index(value = {"id"}, unique = true))
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "dob")
    public String dob;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "state")
    public String state;

    @ColumnInfo(name = "postcode")
    public String postcode;

    @ColumnInfo(name = "age")
    public String age;

    protected User(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        image = in.readString();
        email = in.readString();
        country = in.readString();
        date = in.readString();
        dob = in.readString();
        city = in.readString();
        state = in.readString();
        postcode = in.readString();
        age = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getDobDate(){
        return "Dob : " + getDate(dob);
    }

    public String getDetailEmail() {
        return "Email : " + email;
    }

    public String dateJoined() {
        return "Date Joined : " + getDate(date);
    }

    public String userJoinedDate(){
        return getDate(date);
    }

    public String getCity() {
        return "City : " + city;
    }

    public String getState() {
        return "State : " + state;
    }

    public String getUserCountry() {
        return "Country | " + country;
    }

    public String getDetailCountry() {
        return "Country : " + country;
    }

    public String getPostCode() {
        return "Postcode : " + postcode;
    }

    public User(String name, String image, String email, String country, String date, String dob, String city, String state, String postcode,String age) {
        this.name = name;
        this.image = image;
        this.email = email;
        this.country = country;
        this.date = date;
        this.dob = dob;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.age = age ;
    }

    @BindingAdapter({"avatar"})
    public static void loadImage(ImageView imageView, String imageURL) {
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions()
                        .circleCrop())
                .load(imageURL)
                .into(imageView);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(email);
        dest.writeString(country);
        dest.writeString(date);
        dest.writeString(dob);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(postcode);
        dest.writeString(age);
    }
}