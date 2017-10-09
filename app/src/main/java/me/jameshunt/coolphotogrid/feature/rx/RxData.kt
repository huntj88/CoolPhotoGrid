package me.jameshunt.coolphotogrid.feature.rx

import io.realm.RealmResults
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto

/**
 * Created by James on 10/5/2017.
 */
/*data class RxData(
        val id: BaseApi.API,
        val realmPhotos: RealmResults<RealmPhoto>,
        val index: Int = 0
) {
}*/


interface RxData {

}


// multiple data classes needed?


// one for RxNewImages
    // emitted by ActivityPresenter, and browse page,
    // observed by browse page, and viewer page

    // NOTE: emitted and observed in browse page to keep things consistent
    // this is because when they swipe left or right to switch between new images, and albums





// one for RxAlbumData (only thing it needs is the album/categoryID
    // this is emitted by album page, and observed by browse page
    // can also be emitted by browse page when swiping left right to change album (SO BOTH IN BROWSE)





// one for RxImageClickedData (contains realmPhotos, and id of clicked image)
    // emitted by browser, observed by viewer. sends the realmPhotos with it so that the viewer page is API agnostic
    // MAYBE - when put through the apiFactory it should return one immediately that doesn't do anything but expose the RealmResults (consistency)





// one for RxFavoritesAddedData (contains a list of the realmPhoto ID's that were added to favorites, as well as a list for ones removed)
    // emitted by viewer page, observed by browser page
    // when observed by browser page, check the visible ViewHolders to see if they are part of the ones that were added to favorites. show favorite animation