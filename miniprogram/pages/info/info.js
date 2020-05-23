// pages/info/info.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  },
  changeTofail() {
    wx.navigateTo({
      url: '../fail/fail'
    })
  },
  changeToArch(){
    let _this = this
    console.log(_this.data.reg_id)
    wx.navigateTo({
      url: '../arch/arch?reg_id=' + _this.data.reg_id
    })
  },
  changeToCost() {
    let _this = this
    console.log(_this.data.reg_id)
    wx.navigateTo({
      url: '../cost/cost?reg_id=' + _this.data.reg_id
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      reg_id: options.reg_id

    })

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