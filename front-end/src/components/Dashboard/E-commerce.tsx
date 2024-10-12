"use client";
import dynamic from "next/dynamic";
import React, { useContext, useEffect, useMemo, useRef } from "react";
import CardDataStats from "../CardDataStats";
import SvgOne from "../svg/SvgOne";
import SvgTwo from "../svg/SvgTwo";
import SvgThree from "../svg/SvgThree";
import SvgFour from "../svg/SvgFour";
import ChartOne from "../Charts/ChartOne";
import ChartTwo from "../Charts/ChartTwo";
import TableOne from "../Tables/TableOne";
import { ContextModule } from "../context/ContextProvider";
import convertStringForm from "@/util/convertStringForm";
const ChartThree = dynamic(() => import("@/components/Charts/ChartThree"), {
  ssr: false,
});

const ECommerce: React.FC = () => {
  const context = useContext(ContextModule);

  if (!context) {
    throw new Error("Input must be used within an InputProvider-");
  }

  const { contextValue, setContextValue } = context;
  console.log(contextValue);

  const upperRef = useRef(
    "grid grid-cols-1 gap-4 md:grid-cols-2 md:gap-6 xl:grid-cols-4 2xl:gap-7.5",
  );
  const lowerRef = useRef(
    "mt-4 grid grid-cols-12 gap-4 md:mt-6 md:gap-6 2xl:mt-7.5 2xl:gap-7.5",
  );
  const tableRef = useRef("col-span-12 xl:col-span-8");

  const data = useMemo(() => {
    return [
      {
        title: "시가총액 변화율",
        total: convertStringForm(contextValue.capPct24H, "percentage") || "",
        rate: "0.43%",
        levelUp: true,
        svg: <SvgOne />,
      },
      {
        title: "시가총액 평균치",
        total: convertStringForm(contextValue.capAvg, "converter") || "",
        rate: "2.59%",
        levelUp: true,
        svg: <SvgTwo />,
      },
      {
        title: "암호화폐 갯수",
        total: convertStringForm(contextValue.activeCrypto, "comma") || "",
        rate: "4.35%",
        levelDown: true,
        svg: <SvgThree />,
      },
      {
        title: "최근 업데이트 날짜",
        total: convertStringForm(contextValue.updatedDt, "date") || "",
        svg: <SvgFour />,
      },
    ];
  }, [contextValue]);

  const chartData = useMemo(() => {
    return [
      {
        component: ChartOne,
        title: "최근 7일간 시가총액 변화율",
        data: contextValue.capChange,
      },
      {
        component: ChartTwo,
        title: "총 거래량 TOP",
        data: contextValue.totalVolume,
      },
      {
        component: ChartThree,
        title: "시가총액 비율",
        data: contextValue.capPct,
      },
    ];
  }, [contextValue]);

  return (
    <>
      <div className={upperRef.current}>
        {data.map((item, index) => (
          <CardDataStats
            key={index}
            title={item.title}
            total={item.total}
            rate={item.rate}
            levelUp={item?.levelUp || false}
            levelDown={item?.levelDown || false}
          >
            {item.svg}
          </CardDataStats>
        ))}
      </div>

      <div className={lowerRef.current}>
        {chartData.map((item, index) => {
          const Component = item.component; // 동적으로 컴포넌트 선택
          return <Component key={index} title={item.title} data={item.data} />;
        })}

        <div className={tableRef.current}>
          <TableOne title="금일 트랜드 코인" data={contextValue.searchTrend} />
        </div>
      </div>
    </>
  );
};

export default ECommerce;
