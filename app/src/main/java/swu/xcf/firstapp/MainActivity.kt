package swu.xcf.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    //抽奖名单的保存
    var names= listOf<String>("Avicii","张三","李四","王五","潘剑彬","李相见","张习文")
    //定时器 每隔一段时间切换一次名字
    lateinit var timer:Timer
    var index=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }
    private  fun init(){
        //设置默认显示第一个人的名字
        mNametextView.text=names[0]

        //给按钮添加点击事件
         mStartbutton.setOnClickListener {
             //判断标题是START还是STOP
             if(mStartbutton.text.toString()=="START"){
                 mStartbutton.text="STOP"
                 //创建计时器
                 timer= Timer()
                 //分配一个定时任务
                 timer.schedule(object: TimerTask(){
                     override fun run() {
                         //判断是否越界
                         index=if(index+1>names.size-1) 0 else index+1
                         Log.v("pxd","$index")
                         //取出相应的名字
                         runOnUiThread { mNametextView.text=names[index] }
                     }
                 },0,100)
             }else{
                 mStartbutton.text="START"
                 timer.cancel()
             }
       }
    }
}