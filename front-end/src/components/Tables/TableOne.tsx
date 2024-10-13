"use client";
import Image from "next/image";
import { useEffect, useMemo } from "react";

interface tableProps {
  title: string;
  data: any;
}
// updated_at

// thumb
// symbol
// price
// total_volume
// market_cap_rank
const TableOne: React.FC<tableProps> = ({ title, data }) => {
  const brandData = useMemo(
    () => ({
      updated_at: data.updated_at,
      data: data.coins.map((coin: any) => {
        return {
          thumb: coin.item.thumb,
          symbol: coin.item.symbol,
          price: coin.item.data.price,
          total_volume: coin.item.data.total_volume,
          market_cap_rank: coin.item.market_cap_rank,
        };
      }),
    }),
    [],
  );

  return (
    <div className="table-one rounded-sm border border-stroke bg-white px-5 pb-2.5 pt-6 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1">
      <h4 className="mb-6 text-xl font-semibold text-black dark:text-white">
        {title}
      </h4>

      <div className="overflow-hidden-scroll flex flex-col">
        <div className="sticky-bar grid grid-cols-3 rounded-sm bg-gray-2 dark:bg-meta-4 sm:grid-cols-4">
          <div className="p-2.5 xl:p-5">
            <h5 className="text-sm font-medium uppercase xsm:text-base">
              코인명
            </h5>
          </div>
          <div className="p-2.5 text-center xl:p-5">
            <h5 className="text-sm font-medium uppercase xsm:text-base">
              코인가격
            </h5>
          </div>
          <div className="hidden p-2.5 text-center sm:block xl:p-5">
            <h5 className="text-sm font-medium uppercase xsm:text-base">
              거래량
            </h5>
          </div>
          <div className="hidden p-2.5 text-center sm:block xl:p-5">
            <h5 className="text-sm font-medium uppercase xsm:text-base">
              시가총액 순위
            </h5>
          </div>
        </div>

        {brandData.data.map((brand: any, key: any) => (
          <div
            className={`grid grid-cols-3 sm:grid-cols-4 ${
              key === brandData.data.length - 1
                ? ""
                : "border-b border-stroke dark:border-strokedark"
            }`}
            key={key}
          >
            <div className="flex items-center gap-3 p-2.5 xl:p-5">
              <div className="flex-shrink-0">
                <img src={brand.thumb} alt="Brand" width={48} height={48} />
              </div>
              <p className="hidden text-black dark:text-white sm:block">
                {brand.symbol}
              </p>
            </div>

            <div className="flex items-center justify-center p-2.5 xl:p-5">
              <p className="text-meta-3">{brand.price}</p>
            </div>

            <div className="hidden items-center justify-center p-2.5 sm:flex xl:p-5">
              <p className="text-black dark:text-white">{brand.total_volume}</p>
            </div>

            <div className="hidden items-center justify-center p-2.5 sm:flex xl:p-5">
              <p className="text-meta-5">{brand.market_cap_rank}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default TableOne;
