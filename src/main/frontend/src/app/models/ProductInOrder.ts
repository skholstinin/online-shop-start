import {ProductInfo} from "./productInfo";

export class ProductInOrder {
  productId: string;
  productName: string;
  productPrice: number;
  productStock: number;
  productDescription: string;
  productIcon: string;
  count: number;

  constructor(productInfo: ProductInfo, quantity = 1) {
    this.productId = productInfo.productId;
    this.productName = productInfo.productName;
    this.productPrice = productInfo.productPrice;
    this.productStock = productInfo.productStock;
    this.productDescription = productInfo.productDescription;
    ;
    this.productIcon = productInfo.productIcon;
    this.count = quantity;
  }
}
