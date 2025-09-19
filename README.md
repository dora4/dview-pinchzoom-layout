dview-pinchzoom-layout
![Release](https://jitpack.io/v/dora4/dview-pinchzoom-layout.svg)
--------------------------------

#### 运行效果
![双指缩放](https://github.com/user-attachments/assets/58b94083-f509-4001-8e06-9feca05ad48e)

#### 卡片
![DORA视图 时空旋涡](https://github.com/user-attachments/assets/ec33d344-fa9a-4bd9-8193-8293985ffd0e)

#### 规范标准
此控件遵循《Dora View规范手册》 https://github.com/dora4/dview-template/blob/main/Naming_Convention_Guide.md

#### Gradle依赖配置

```groovy
// 添加以下代码到项目根目录下的build.gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
// 添加以下代码到app模块的build.gradle
dependencies {
    implementation 'com.github.dora4:dview-pinchzoom-layout:1.0'
}
```

#### 使用方式
activity_pinch_zoom_layout.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context=".ui.PinchZoomLayoutActivity">

    <data>

    </data>

    <androidx.appcompat.widget.LinearLayoutCompat
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <dora.widget.DoraTitleBar
            android:id="@+id/titleBar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@color/colorPrimary"
            app:dview_title="@string/common_title" />

        <FrameLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <dora.widget.DoraPinchZoomLayout
                android:id="@+id/imageViewer"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="@color/black">

                <dora.widget.Issue4ViewPager
                    android:id="@+id/viewPager"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent" />
            </dora.widget.DoraPinchZoomLayout>
        </FrameLayout>
    </androidx.appcompat.widget.LinearLayoutCompat>
</layout>
```
Kotlin代码。
```kt
binding.imageViewer.setTouchListener(object : DoraPinchZoomLayout.TouchListener {
            override fun onClick(v: View, e: MotionEvent) {
                Toast.makeText(this@PinchZoomLayoutActivity, "单击(${e.rawX},${e.rawY})", Toast.LENGTH_SHORT).show()
            }

            override fun onDoubleClick(v: View, e: MotionEvent) {
                Toast.makeText(this@PinchZoomLayoutActivity, "双击(${e.rawX},${e.rawY})", Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick(v: View, e: MotionEvent) {
                Toast.makeText(this@PinchZoomLayoutActivity, "长按(${e.rawX},${e.rawY})", Toast.LENGTH_SHORT).show()
            }

        })
```
