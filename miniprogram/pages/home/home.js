// pages/home/home.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    reg_id:'',
    arch:'',
  },
  scancode: function () {
    let _this = this
    // 允许从相机和相册扫码
    wx.scanCode({
      success(res) {
        console.log(res);
        wx.navigateTo({
          url: '../info/info?reg_id=' + res.result
        })
        var result = res.result;
        _this.setData({
          reg_id: result,
        })
        wx.showToast({
          title: '成功',
          icon: 'success',
          duration: 2000
        })
       
      },
      fail: (res) => {
        console.log(res);
        wx.showToast({
          title: '失败',
          icon: 'none',
          duration: 2000
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})