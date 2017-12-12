package com.example.adeogo.silavoscresenye.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.adapter.VideoAdapter;
import com.example.adeogo.silavoscresenye.model.Video;
import com.example.adeogo.silavoscresenye.utilities.JSONUtil;
import com.example.adeogo.silavoscresenye.utilities.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Adeogo on 10/1/2017.
 */

public class VideoActivity extends AppCompatActivity implements VideoAdapter.VideoAdapterOnclickHandler {

    private static final String BASE_URL = "https://www.googleapis.com/youtube/v3/search?order=date&part=snippet&channelId=UC_x5XG1OV2P6uZZ5FSM9Ttw&maxResults=25&key=AIzaSyDUU4xuxuxG4RFatlNWQwi4Kw-gUAQ1AAg";
    private RecyclerView mRecyclerView;
    private VideoAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<Video> mVideoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.video_rv);
        mAdapter = new VideoAdapter(this, this);
        mManager = new LinearLayoutManager(this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mManager);

        VideoTask videoTask = new VideoTask();
        videoTask.execute(BASE_URL);


    }

    @Override
    public void voidMethod(List<Video> ListVideo, int adapterPosition) {
        Video video = ListVideo.get(adapterPosition);

        Intent intent = new Intent(this, ViewVideoActivity.class);
        intent.putExtra("video_url", video.getUrl());
        startActivity(intent);
    }

    private class VideoTask extends AsyncTask<String, Void, List<Video>>{

        @Override
        protected List<Video> doInBackground(String... params) {
            List<Video> videoList = null;
            JSONArray array = null;
            try {
                array = JSONUtil.getYoutubeArray("{\n" +
                        " \"kind\": \"youtube#searchListResponse\",\n" +
                        " \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/Ea0-HHn1qnfa0c0VRWyG8LBxdyg\\\"\",\n" +
                        " \"nextPageToken\": \"CBkQAA\",\n" +
                        " \"regionCode\": \"RU\",\n" +
                        " \"pageInfo\": {\n" +
                        "  \"totalResults\": 95,\n" +
                        "  \"resultsPerPage\": 25\n" +
                        " },\n" +
                        " \"items\": [\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/lssnUuylfQi88JEeqoQpFgqnqYs\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"WaF05EzC0ww\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-10-01T11:05:01.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream 1.10.2017 12.20 PM\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/WaF05EzC0ww/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/WaF05EzC0ww/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/WaF05EzC0ww/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/YUlGJqwUzzVILYANMztA-icj43E\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"rZLt6lVPa5o\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-10-01T09:02:44.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream 1.10.2017 10.20 AM\",\n" +
                        "    \"description\": \"My Event Description.\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/rZLt6lVPa5o/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/rZLt6lVPa5o/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/rZLt6lVPa5o/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/v0vMNspO5rfHdtli4z6iS4-vFME\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"VlxJPVi-usk\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-30T19:18:15.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Эпизод 01: необходимость рождения свыше - Сергей Горохов\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/VlxJPVi-usk/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/VlxJPVi-usk/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/VlxJPVi-usk/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/Giif2hL28tjwDCatcjOOWspMWJU\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"7lzdJZMbkfU\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-30T19:18:34.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Эпизод 03: Новая природа против старого образа жизни - Сергей Горохов\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/7lzdJZMbkfU/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/7lzdJZMbkfU/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/7lzdJZMbkfU/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/Z_YJPfFVdoGBSLKR6l5OpUONigY\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"MSohGlnjYl4\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-30T19:18:01.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Эпизод 07: Как расти духовно - Сергей Горохов\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/MSohGlnjYl4/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/MSohGlnjYl4/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/MSohGlnjYl4/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/ltB5JtFb6c6aQ7gOQeWIODNxAKo\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"W6YQyW3cOs8\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-30T19:18:46.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Эпизод 02: Новая природа - Сергей Горохов\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/W6YQyW3cOs8/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/W6YQyW3cOs8/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/W6YQyW3cOs8/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/5Tily3DkTVRso3yqDBuXuyMEiGc\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"tPORY0l8MO0\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-30T19:17:03.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Эпизод 05: Знай кто ты - Сергей Горохов\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/tPORY0l8MO0/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/tPORY0l8MO0/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/tPORY0l8MO0/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/z13S_0DTZtWt8aGUvQ7Q91K3Agg\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"XLKrDQx0MfA\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-26T21:00:53.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Пастор Сергей Горохов - Представьте тела ваши в жертву\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/XLKrDQx0MfA/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/XLKrDQx0MfA/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/XLKrDQx0MfA/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/dVsKzCkY3YYYslwX8kUXdDRUrCU\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"K9zBC-Px4pk\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-24T10:55:55.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream 24.09.2017 12.20 PM\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/K9zBC-Px4pk/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/K9zBC-Px4pk/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/K9zBC-Px4pk/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/0QSWnY_Ynrw14mEr3Fw8ckljnT8\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"HSkz8svXPck\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-24T08:52:05.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream 24.09.2017 10.20 AM\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/HSkz8svXPck/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/HSkz8svXPck/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/HSkz8svXPck/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/mqFDitEdeSU1QCeqQp5wcIwPGmU\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"bWc0O5qnLSE\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-19T11:11:57.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Пастор Сергей Горохов - Спасён, а Что дальше?\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/bWc0O5qnLSE/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/bWc0O5qnLSE/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/bWc0O5qnLSE/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/xQGXks2lYjLDke0fAvuSxyd0HEI\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"ind7wax720U\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-18T13:07:35.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream 17.09.2017 10.20 AM\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/ind7wax720U/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/ind7wax720U/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/ind7wax720U/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/FPMKWC9D_vM3Drt8kuo9Amo6BTw\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"qm6vEAFW8W8\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-17T11:05:53.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream 17.09.2017 12.20 PM\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/qm6vEAFW8W8/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/qm6vEAFW8W8/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/qm6vEAFW8W8/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/oD82NdbpaAFK3ZN8fZWWjI7oGAA\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"GyJmHOo-EkY\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-13T12:44:32.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Пастор Сергей Горохов   Христианская семья\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/GyJmHOo-EkY/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/GyJmHOo-EkY/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/GyJmHOo-EkY/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/nS2pPcm0NxmDFZu80DCIItbfQCw\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"duDaA2cyHds\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-05T09:20:02.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сергей и Нина Лучко - Эстафета Веры\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/duDaA2cyHds/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/duDaA2cyHds/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/duDaA2cyHds/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/8xSQ-aDWx795AeaoKEPPmoXoWW0\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"hV8kIsAlvaM\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-03T11:02:02.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream 03.09.2017 12.20 PM\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/hV8kIsAlvaM/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/hV8kIsAlvaM/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/hV8kIsAlvaM/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/J9G8mV1dm1QAZXhLkepGeQH-peI\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"LibNoLf7Qf4\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-09-03T08:48:21.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream 03.09.2017 10.20 AM\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/LibNoLf7Qf4/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/LibNoLf7Qf4/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/LibNoLf7Qf4/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/GhyXr0dbztoYv2e0SjJ7HIvK0w0\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"4nkT3FYUXXU\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-08-30T14:35:06.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Пастор Сергей Горохов - Слышать Бога через размышление над Библией\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/4nkT3FYUXXU/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/4nkT3FYUXXU/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/4nkT3FYUXXU/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/HvjyU6ulOTKyYEialp4dNowImWM\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"m_SlytKK7rU\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-08-27T11:31:51.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream 27.08.2017 12.20 PM\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/m_SlytKK7rU/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/m_SlytKK7rU/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/m_SlytKK7rU/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/cwuKHAx7wYFHmxpv35TMhA0bX28\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"kFAxnN8PChA\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-08-20T10:54:37.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream 20.08.2017 12.00\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/kFAxnN8PChA/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/kFAxnN8PChA/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/kFAxnN8PChA/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/VHR4Ux0J4XR9iymE2MFl2daJZbw\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"GB0EZtBzyk4\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-08-20T09:39:55.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream 20.08.2017 10.00\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/GB0EZtBzyk4/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/GB0EZtBzyk4/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/GB0EZtBzyk4/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/_Bsc4pVjHLMIslBvyDNsJkUwTjQ\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"Qh9nCB5LU8M\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-08-06T16:50:52.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream 12.00\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/Qh9nCB5LU8M/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/Qh9nCB5LU8M/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/Qh9nCB5LU8M/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/BOnXL6XFjQ833tmXGdpwv3yMVp0\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"VCzZwYXjpbs\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-08-06T09:21:56.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/VCzZwYXjpbs/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/VCzZwYXjpbs/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/VCzZwYXjpbs/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/z1UPwx7Ov-pgAvrZVsfd_fPdZgQ\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"tRrrVkCL18Q\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-08-06T07:57:01.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Сила Воскресения г.Казань Live Stream\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/tRrrVkCL18Q/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/tRrrVkCL18Q/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/tRrrVkCL18Q/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"kind\": \"youtube#searchResult\",\n" +
                        "   \"etag\": \"\\\"VPWTmrH7dFmi4s1RqrK4tLejnRI/pZy2YIN6uyoERcRPJ3e43ZWFP9Y\\\"\",\n" +
                        "   \"id\": {\n" +
                        "    \"kind\": \"youtube#video\",\n" +
                        "    \"videoId\": \"7FuKbRUGq_E\"\n" +
                        "   },\n" +
                        "   \"snippet\": {\n" +
                        "    \"publishedAt\": \"2017-07-18T21:57:38.000Z\",\n" +
                        "    \"channelId\": \"UCYjTuPtp6L43DRrglAis56w\",\n" +
                        "    \"title\": \"Пастор Сергей Горохов - Как нам ходить в любви\",\n" +
                        "    \"description\": \"\",\n" +
                        "    \"thumbnails\": {\n" +
                        "     \"default\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/7FuKbRUGq_E/default.jpg\",\n" +
                        "      \"width\": 120,\n" +
                        "      \"height\": 90\n" +
                        "     },\n" +
                        "     \"medium\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/7FuKbRUGq_E/mqdefault.jpg\",\n" +
                        "      \"width\": 320,\n" +
                        "      \"height\": 180\n" +
                        "     },\n" +
                        "     \"high\": {\n" +
                        "      \"url\": \"https://i.ytimg.com/vi/7FuKbRUGq_E/hqdefault.jpg\",\n" +
                        "      \"width\": 480,\n" +
                        "      \"height\": 360\n" +
                        "     }\n" +
                        "    },\n" +
                        "    \"channelTitle\": \"Sergei Gorokhov\",\n" +
                        "    \"liveBroadcastContent\": \"none\"\n" +
                        "   }\n" +
                        "  }\n" +
                        " ]\n" +
                        "}");
                videoList = JSONUtil.getVideoList(array);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return videoList;
        }

        @Override
        protected void onPostExecute(List<Video> videos) {
            super.onPostExecute(videos);
            mAdapter.swapData(videos);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
