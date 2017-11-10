ImagesViewPager
===============

Simple app to show how to extend `PagerAdapter`

`ImagesPagerAdapter` extends `PagerAdapter` using `ImageView` as the *key* object to identified the views added to the Layout (ViewPager).

So `instantiateItem` method returns the added ImageView and the `isViewFromObject` method identifiy views comparing them directly with key object. `destroyItem` just removes the key (`ImageView`) from the Layout.

A very *important method to override* is `getItemPosition` to tell `ViewPager` if an Item needs to be updated
