"use client";

import { ContextModule } from "@/components/context/ContextProvider";
import { useContext, useEffect, useState } from "react";
import Loader from "@/components/common/Loader";

export default function ApiClient({ children }: { children: React.ReactNode }) {
  const context = useContext(ContextModule);

  if (!context) {
    throw new Error("Input must be used within an InputProvider-");
  }

  const { contextValue, setContextValue } = context;
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    getMovies();
  }, []);

  async function getMovies() {
    const BASE_URL = process.env.NEXT_PUBLIC_BACK_END_BASE_URL;

    try {
      const response = await fetch(`${BASE_URL}/statistic/`);
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      const data = await response.json();
      setContextValue(
        "capPct24H",
        data.resultList.data.marketCapChangePercentage24hUsd,
      );
      setContextValue(
        "activeCrypto",
        data.resultList.data.activeCryptocurrencies,
      );
      setContextValue("capAvg", data.capAverage.averageTotalMarketCap);
      setContextValue("updatedDt", data.resultList.data.updatedAt);

      setContextValue("capChange", data.capPercentage7days);
      setContextValue("totalVolume", data.resultList.sortTotalVolume);
      setContextValue("capPct", data.resultList.data.marketCapPercentage);
      setContextValue("searchTrend", data.searchTrend);

      setLoading(false);
    } catch (error) {
      console.error("Failed to fetch movies:", error);
    }
  }

  return <>{loading ? <Loader /> : children};</>;
}
