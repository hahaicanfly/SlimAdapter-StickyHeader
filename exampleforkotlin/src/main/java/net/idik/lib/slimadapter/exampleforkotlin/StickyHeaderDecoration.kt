package net.idik.lib.slimadapter.exampleforkotlin

import android.content.Context
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import net.idik.lib.slimadapter.SlimAdapter
import net.idik.lib.slimadapter.exampleforkotlin.databinding.ItemHeaderViewBinding

/**
 * Created on 6/7/21.
 */
class StickyHeaderDecoration(private val adapter: SlimAdapter, context: Context) : RecyclerView.ItemDecoration() {

    private val headerBinding by lazy { ItemHeaderViewBinding.inflate(LayoutInflater.from(context)) }
    private val headerView: View
        get() = headerBinding.root

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val topChild = parent.getChildAt(0)

        parent.getChildAdapterPosition(topChild)
                .let { topChildPosition ->
                    val headerIndex = adapter.data.indexOf(StickerHeader(0))
                    headerBinding.tvHeader.visibility =
                            if (topChildPosition >= headerIndex) View.VISIBLE else View.GONE
                    layoutHeaderView(topChild)
                    canvas.drawHeaderView()
                }
    }

    private fun layoutHeaderView(topView: View?) {
        topView?.let {
            headerView.measure(
                    View.MeasureSpec.makeMeasureSpec(topView.width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
            )
            headerView.layout(topView.left, 0, topView.right, headerView.measuredHeight)
        }
    }

    private fun Canvas.drawHeaderView() {
        save()
        headerView.draw(this)
        restore()
    }
}