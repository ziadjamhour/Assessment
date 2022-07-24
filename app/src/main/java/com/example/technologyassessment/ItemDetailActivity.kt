package com.example.technologyassessment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.example.technologyassessment.model.ArticleModel

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val article =
            this.getIntent().getSerializableExtra(ItemDetailFragment.ARG_ARTICLE) as? ArticleModel

        if (savedInstanceState == null) {

            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(
                        ItemDetailFragment.ARG_ARTICLE,
                        article
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {

                navigateUpTo(Intent(this, ItemListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}