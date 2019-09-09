
package com.e.nytimes.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NewsResponse implements Parcelable {
    private String status;
    private String copyright;
    private int numResults;
    private List<Result> results = null;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.copyright);
        dest.writeInt(this.numResults);
        dest.writeList(this.results);
    }

    protected NewsResponse(Parcel in) {
        this.status = in.readString();
        this.copyright = in.readString();
        this.numResults = in.readInt();
        this.results = new ArrayList<Result>();
        in.readList(this.results, Result.class.getClassLoader());
    }

    public static final Parcelable.Creator<NewsResponse> CREATOR = new Parcelable.Creator<NewsResponse>() {
        @Override
        public NewsResponse createFromParcel(Parcel source) {
            return new NewsResponse(source);
        }

        @Override
        public NewsResponse[] newArray(int size) {
            return new NewsResponse[size];
        }
    };
}
