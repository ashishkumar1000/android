
package com.e.nytimes.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Medium implements Parcelable {
    private String type;
    private String subtype;
    private String caption;
    private String copyright;
    private int approvedForSyndication;
    private List<MediaMetadatum> mediaMetadata = null;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.subtype);
        dest.writeString(this.caption);
        dest.writeString(this.copyright);
        dest.writeInt(this.approvedForSyndication);
        dest.writeTypedList(this.mediaMetadata);
    }

    protected Medium(Parcel in) {
        this.type = in.readString();
        this.subtype = in.readString();
        this.caption = in.readString();
        this.copyright = in.readString();
        this.approvedForSyndication = in.readInt();
        this.mediaMetadata = in.createTypedArrayList(MediaMetadatum.CREATOR);
    }

    public static final Parcelable.Creator<Medium> CREATOR = new Parcelable.Creator<Medium>() {
        @Override
        public Medium createFromParcel(Parcel source) {
            return new Medium(source);
        }

        @Override
        public Medium[] newArray(int size) {
            return new Medium[size];
        }
    };
}
