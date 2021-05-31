package com.example.fms;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class Token extends AppCompatActivity {
    Button btn_CreatePush;

    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btn_CreatePush = findViewById(R.id.btn_CreatePush);
    btn_CreatePush.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NotificationManager notification = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

            String id = "message_push";
            CharSequence name = "message push";
                    String description = "푸시 알림 테스트입니다.";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            // 채널을 생성해줍니다!
            NotificationChannel push_channel = new NotificationChannel(id, name, importance);


            push_channel.setDescription(description);

            notification.createNotificationChannel(push_channel); //채널을 등록해줍니다!

            int notifyID = 1; //알림의 ID
            String CHANNEL_ID = "message_push";

            Drawable drawable = getResources().getDrawable(R.drawable.bell);
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

            //알림 채널에 push라는 알림을 만들어 연결합니다!
            Notification push = new Notification.Builder(Token.this, CHANNEL_ID)

                    .setContentTitle("New Message")
                    .setContentText("장지민님으로부터 사진 도착!!") //데이터베이스에 저장된 사용자의 이름과 카테고리로 수정 필요
                    .setSmallIcon(R.drawable.bell)
                    .setChannelId(CHANNEL_ID)
                    .build();
            NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
            style.bigPicture(bitmap).setBigContentTitle("dd");


            notification.notify(notifyID, push);
        }
    });
    }






}